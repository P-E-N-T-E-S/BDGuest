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
    }).then(response => console.log(response));
}

function getCPF(){
    const cpf = document.getElementById('cpf_get').value;

    recuperarDadosPorCPF(cpf)
}

function recuperarDadosPorCPF(cpf) {
    fetch(`http://localhost:8080/pessoa/cpf?valor=${cpf}`)
        .then(response => {
            console.log("Status da resposta:", response.status); // Verifica o status da resposta
            if (!response.ok) {
                throw new Error("Usuário não encontrado");
            }
            return response.json();
        })
        .then(usuario => {
            console.log("Dados do usuário retornados:", usuario); // Log para verificar o conteúdo dos dados recebidos

            // Limpa a lista antes de exibir os dados
            document.querySelector('ul').innerHTML = '';

            // Verifica se o `usuario` tem a propriedade `nome` ou é um objeto dentro de um array
            if (Array.isArray(usuario)) {
                // Caso a API retorne um array
                usuario.forEach(u => {
                    const markup = `<li>${u.nome ? u.nome : "Nome não disponível"}</li>`;
                    document.querySelector('ul').insertAdjacentHTML('beforeend', markup);
                });
            } else if (usuario && usuario.nome) {
                // Caso a API retorne um objeto diretamente com a propriedade `nome`
                const markup = `<li>${usuario.nome}</li>`;
                document.querySelector('ul').insertAdjacentHTML('beforeend', markup);
            } else {
                console.log("Usuário não encontrado ou estrutura de dados inesperada");
            }
        })
        .catch(error => console.error("Erro na busca:", error));
}

function getCPFd(){
    const cpf = document.getElementById('cpf_get_d').value;

    deletarDadosCPF(cpf)
}


function deletarDadosCPF(cpf){
    fetch(`http://localhost:8080/pessoa/cpf?valor=${cpf}`, {method: 'DELETE' , headers: {'Content-Type': 'application/json'}})
}
