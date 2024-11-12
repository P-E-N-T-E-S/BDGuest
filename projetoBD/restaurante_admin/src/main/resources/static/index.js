function enviarDados() {
    const dados = {
        cpf: document.getElementById('cpf').value,
        nome: document.getElementById('nome').value,
        rua: document.getElementById('rua').value,
        bairro: document.getElementById('bairro').value,
        estado: document.getElementById('estado').value,
        cidade: document.getElementById('cidade').value,
        cep: document.getElementById('cep').value,
        email: document.getElementById('email').value,
        data_nascimento: document.getElementById('data_nascimento').value,
        telefone: document.getElementById('telefone').value,
        telefone2: document.getElementById('telefone2').value
    };

    fetch('http://localhost:8080/pessoa', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'  // Isso garante que o servidor sabe que está recebendo JSON.
        },
        body: JSON.stringify(dados) // Transforma o objeto em JSON
    });
}

function recuperarDados() {
    fetch('http://localhost:8080/pessoa')
        .then(response => response.json())  // Espera a resposta e a converte para JSON
        .then(data => console.log(data));}


function deletarDados(){
    fetch('http://localhost:8080/pessoa/cpf?valor=08674743439', {method: 'DELETE' , headers: {'Content-Type': 'application/json'}})
}
