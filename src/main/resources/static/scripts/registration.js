'use strict';

const baseUrl = 'http://localhost:8080';
const csrfToken = document.querySelector('[name=_csrf_token]').content;

const registrationForm = document.getElementById('registration-form');
registrationForm.addEventListener('submit', onRegisterHandler);

async function onRegisterHandler(e) {
    e.preventDefault();
    const form = e.target;
    const data = new FormData(form);
    await createUser(data);
}

async function createUser(userFormData) {

    const userJSON = JSON.stringify(Object.fromEntries(userFormData));
    const settings = {
        method: 'POST',
        cache: 'no-cache',
        mode : 'cors',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken,
        },
        body: userJSON
    };
    await fetch(baseUrl + '/api/customers', settings);
    window.location.href = baseUrl + "/login";
}