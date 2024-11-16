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
                alert('CPF já cadastrado no sistema');
            }
            return response.json();
        })
        .then(data => {
            alert('Dados enviados com sucesso!');
            console.log(data);
        })
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
                            <button class="table-button"" onclick="editarDadosEmail('${u.email}')">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="deletarDadosCPF('${u.cpf}')">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="m696-440-56-56 83-84-83-83 56-57 84 84 83-84 57 57-84 83 84 84-57 56-83-83-84 83Zm-336-40q-66 0-113-47t-47-113q0-66 47-113t113-47q66 0 113 47t47 113q0 66-47 113t-113 47ZM40-160v-112q0-34 17.5-62.5T104-378q62-31 126-46.5T360-440q66 0 130 15.5T616-378q29 15 46.5 43.5T680-272v112H40Zm80-80h480v-32q0-11-5.5-20T580-306q-54-27-109-40.5T360-360q-56 0-111 13.5T140-306q-9 5-14.5 14t-5.5 20v32Zm240-320q33 0 56.5-23.5T440-640q0-33-23.5-56.5T360-720q-33 0-56.5 23.5T280-640q0 33 23.5 56.5T360-560Zm0-80Zm0 400Z"/></svg>
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
            alert('Não foi possível deletar. Usuário possui associação como Cliente ou Funcionário' );
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

function editarDados(cpf) {
    const userData = JSON.parse(localStorage.getItem('userData'));
    if (!userData) {
        alert('Dados do usuário não encontrados.');
        return;
    }

    const campo = document.getElementById('classe_valor').value;
    const valor = document.getElementById('valor_alterado').value;

    const data = { campo, valor };

    fetch(`http://localhost:8080/pessoa/email?valor=${cpf}`, {
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

function getCPF_c() {
    const cpf = document.getElementById('cpf_get_cliente').value;
    recuperarDadosPorCPF_c(cpf);
}

function recuperarDadosPorCPF_c(cpf) {
    fetch(`http://localhost:8080/cliente/cpf?valor=${cpf}`)
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
            console.log(usuario)

            usuarios.forEach(u => {
                const row = `
                    <tr class="text">
                        <td>${u.pessoa.nome || 'Não disponível'}</td>
                        <td>${u.cpf || 'Não disponível'}</td>
                        <td>${u.pessoa.email || 'Não disponível'}</td>
                        <td>
                            <button class="table-button" onclick="editarDados('${u.cpf}')">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="deletarDadosClienteCPF('${u.cpf}')">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="m696-440-56-56 83-84-83-83 56-57 84 84 83-84 57 57-84 83 84 84-57 56-83-83-84 83Zm-336-40q-66 0-113-47t-47-113q0-66 47-113t113-47q66 0 113 47t47 113q0 66-47 113t-113 47ZM40-160v-112q0-34 17.5-62.5T104-378q62-31 126-46.5T360-440q66 0 130 15.5T616-378q29 15 46.5 43.5T680-272v112H40Zm80-80h480v-32q0-11-5.5-20T580-306q-54-27-109-40.5T360-360q-56 0-111 13.5T140-306q-9 5-14.5 14t-5.5 20v32Zm240-320q33 0 56.5-23.5T440-640q0-33-23.5-56.5T360-720q-33 0-56.5 23.5T280-640q0 33 23.5 56.5T360-560Zm0-80Zm0 400Z"/></svg>
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

function cadastrar_funcionario() {
    const dados_cliente = {
        cpf: document.getElementById('cpf').value,
        fidelidade: document.getElementById('fidelidade').value,
        metodo_pagamento_1: document.getElementById('metodo_pagamento_1').value,
        metodo_pagamento_2: document.getElementById('metodo_pagamento_2').value,
    };

    fetch('http://localhost:8080/cliente', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dados_cliente)
    })
        .then(response => {
            if (!response.ok) {
                alert('CPF já cadastrado no sistema');
            }
            return response.json();
        })
        .then(data => {
            alert('Dados enviados com sucesso!');
            console.log(data);
        })
}

function deletarDadosClienteCPF(cpf) {
    fetch(`http://localhost:8080/cliente/cpf?valor=${cpf}`, {
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
            getCPF_c();
        })
        .catch(error => {
            alert('Não foi possível deletar. Usuário possui associação como Cliente ou Funcionário' );
        });
}