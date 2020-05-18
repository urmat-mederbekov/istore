'use strict';

const loginForm = document.getElementById('login-form');
loginForm.addEventListener('submit', onLoginHandler);


function onLoginHandler(e) {
    e.preventDefault();
    const form = e.target;
    const userFormData = new FormData(form);
    const user = Object.fromEntries(userFormData);
    saveUser(user);
}
function saveUser(user) {
    const userAsJSON = JSON.stringify(user)
    localStorage.setItem('user', userAsJSON);
}
function restoreUser() {
    const userAsJSON = localStorage.getItem('user');
    const user = JSON.parse(userAsJSON);
    return user;
}
function updateOptions(options) {
    const update = { ...options };
    update.mode = 'cors';
    update.headers = { ... options.headers };
    update.headers['Content-Type'] = 'application/json';
    const user = restoreUser();
    if(user) {
        update.headers['Authorization'] = 'Basic ' + btoa(user.username + ':' + user.password);
    }
    return update;
}
function fetchAuthorised(url, options) {
    const settings = options || {}
    return fetch(url, updateOptions(settings));
}