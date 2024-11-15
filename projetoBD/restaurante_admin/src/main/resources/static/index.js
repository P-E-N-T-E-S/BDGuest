function toggleSubMenu(button) {
    button.nextElementSibling.classList.toggle('show');
    button.classList.toggle('rotate');
}

function enviarDados() {
    const dados_pessoa = {
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
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dados_pessoa)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao enviar os dados.');
            }
            return response.json();
        })
        .then(data => {
            alert('Dados enviados com sucesso!');
            console.log(data);
        })
        .catch(error => {
            alert('Erro ao enviar dados: ' + error.message);
        });
}

function getCPF() {
    const cpf = document.getElementById('cpf_get').value;
    recuperarDadosPorCPF(cpf);
}

function recuperarDadosPorCPF(cpf) {
    fetch(`http://localhost:8080/pessoa/cpf?valor=${cpf}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Usuário não encontrado');
            }
            return response.json();
        })
        .then(usuario => {
            const tabela = document.getElementById('tabelaUsuarios').querySelector('tbody');
            tabela.innerHTML = '';

            const usuarios = Array.isArray(usuario) ? usuario : [usuario];
            usuarios.forEach(u => {
                const row = `
                    <tr class="text">
                        <td>${u.nome || 'Não disponível'}</td>
                        <td>${u.cpf || 'Não disponível'}</td>
                        <td>${u.email || 'Não disponível'}</td>
                        <td>
                            <button class="botao-tabela" onclick="editarDadosEmail('${u.email}')">
                                Editar
                            </button>
                        </td>
                        <td>
                            <button class="botao-tabela" onclick="deletarDadosCPF('${u.cpf}')">
                                Deletar
                            </button>
                        </td>
                    </tr>`;
                tabela.insertAdjacentHTML('beforeend', row);
            });
        })
        .catch(error => {
            alert('Erro ao buscar dados: ' + error.message);
        });
}

function deletarDadosCPF(cpf) {
    fetch(`http://localhost:8080/pessoa/cpf?valor=${cpf}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao deletar usuário');
            }
            alert('Usuário deletado com sucesso!');
            getCPF();
        })
        .catch(error => {
            alert('Erro ao deletar usuário: ' + error.message);
        });
}

function getEmail() {
    const email = document.getElementById('email_get').value;
    editarDadosEmail(email);
}

function editarDadosEmail(email) {
    fetch(`http://localhost:8080/pessoa/email?valor=${email}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar dados para edição.');
            }
            return response.json();
        })
        .then(data => {
            const userData = data[0] || {};
            if (!userData.cpf) {
                throw new Error('Dados não encontrados.');
            }
            localStorage.setItem('userData', JSON.stringify(userData));
            window.location.href = '/editar_pessoa';
        })
        .catch(error => {
            alert('Erro ao buscar dados: ' + error.message);
        });
}

document.addEventListener('DOMContentLoaded', function () {
    const userData = JSON.parse(localStorage.getItem('userData'));
    if (userData) {
        const campos = ['cpf', 'nome', 'rua', 'bairro', 'estado', 'cidade', 'cep', 'email', 'data_nascimento', 'telefone', 'telefone2'];
        campos.forEach(campo => {
            const input = document.getElementById(`${campo}_edit`);
            if (input) input.value = userData[campo] || '';
        });
    }
});

function editarDados() {
    const userData = JSON.parse(localStorage.getItem('userData'));
    if (!userData) {
        alert('Dados do usuário não encontrados.');
        return;
    }

    const campo = document.getElementById('classe_valor').value;
    const valor = document.getElementById('valor_alterado').value;

    const data = { campo, valor };

    fetch(`http://localhost:8080/pessoa/email?valor=${userData.email}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao atualizar dados.');
            }
            alert('Dados atualizados com sucesso!');
        })
        .catch(error => {
            alert('Erro ao atualizar dados: ' + error.message);
        });
}