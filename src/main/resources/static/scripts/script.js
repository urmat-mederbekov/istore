
const baseUrl = 'http://localhost:8080';
const restUrl = 'http://localhost:8080/api';
async function getTypes() {
    const response = await fetch(restUrl + '/product-types');
    if (response.ok) {
        return await response.json();
    } else {
        alert("Error" + response.error());
    }
}
async function load(){
    const types = await getTypes();
    types.forEach(type => {

        let card = document.createElement('a');
        card.href = `/${type.name.toLocaleLowerCase()}s`;
        card.className = 'card d-inline-block';
        card.style.width = '18rem';
        card.innerHTML =
            `<div class="card d-inline-block" style="width: 18rem">
                <img class="card-img-top" style="height: 65%" src="/images/${type.icon}" alt="Card image cap">
                <div class="card-body"">
                <h5 class="card-title">${type.name}</h5>
            <!--        <a href="#" class="btn btn-primary">Go somewhere</a>-->
                </div>
            </div>`;
        document.getElementsByClassName('row')[0].append(card);
//         let card = document.createElement('div');
//         card.className = 'card d-inline-block';
//         card.style.width = '18rem';
//         card.innerHTML =
//             `<img class="card-img-top" style="height: 65%" src="/images/${type.icon}" alt="Card image cap">
//             <div class="card-body"">
//             <h5 class="card-title">${type.name}</h5>
// <!--        <a href="#" class="btn btn-primary">Go somewhere</a>-->
//         </div>`;
//         document.getElementsByClassName('row')[0].append(card);
    })
}

load();