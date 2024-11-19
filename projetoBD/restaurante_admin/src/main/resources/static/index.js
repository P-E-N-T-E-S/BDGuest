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
        })
        .then(data => {
            alert('Dados enviados com sucesso!');
            console.log(data);
        })
}

function getCPF() {
    const cpf = document.getElementById('cpf_get').value;
    const filtro = document.getElementById('campo_pesquisa').value
    recuperarDadosPorCPF(cpf, filtro);
}

function recuperarDadosPorCPF(cpf, filtro) {
    fetch(`http://localhost:8080/pessoa/${filtro}?valor=${cpf}`)
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
    const userData = JSON.parse(localStorage.getItem('userData'));
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
            console.log(userData)
        });
}

document.addEventListener('DOMContentLoaded', function () {
    const userData = JSON.parse(localStorage.getItem('userData'));
    if (userData) {
        const campos = ['cpf'];
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
            console.log(userData.cpf)
        })
        .catch(error => {
            alert('Erro ao atualizar dados: ' + error.message);
        });
}

function getCPF_c() {
    const cpf = document.getElementById('cpf_get_cliente').value;
    const filtro = document.getElementById('campo_pesquisa').value
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
                        <td>${u.fidelidade}</td>
                        <td>${u.metodo_pagamento_1 || 'Não disponível'}</td>
                        <td>
                            <button class="table-button" onclick="editarDadosCPF_cliente('${u.cpf}')">
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

function cadastrar_cliente() {
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

function editarDadosCPF_cliente(cpf) {
    const userData = JSON.parse(localStorage.getItem('userData'));
    fetch(`http://localhost:8080/cliente/cpf?valor=${cpf}`)
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
            window.location.href = '/editar_cliente';
        })
        .catch(error => {
            alert('Erro ao buscar dados: ' + error.message);
            console.log(userData)
        });
}

function editarDados_cliente(cpf) {
    const userData = JSON.parse(localStorage.getItem('userData'));
    if (!userData) {
        alert('Dados do usuário não encontrados.');
        return;
    }

    const campo = document.getElementById('classe_valor').value;
    const valor = document.getElementById('valor_alterado').value;
    console.log(userData)

    const data = { campo, valor };

    fetch(`http://localhost:8080/cliente/cpf?valor=${userData.cpf}`, {
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
            console.log(userData.cpf)
        })
        .catch(error => {
            alert('Erro ao atualizar dados: ' + error.message);
        });
}

function getCPF_funcionario() {
    const cpf = document.getElementById('cpf_get_funcionario').value
    const filtro = document.getElementById('campo_pesquisa').value

    recuperarDadosPorCPF_funcionario(cpf);
}

function cadastrar_funcionario() {
    // Gather data from the form
    const dados_funcionario = {
        cpf: document.getElementById('cpf').value.trim(),
        data_contratacao: document.getElementById('data_contratacao').value.trim(),
        salario: parseFloat(document.getElementById('salario').value.trim()),
        horario_entrada: document.getElementById('horario_entrada').value.trim(),
        horario_saida: document.getElementById('horario_saida').value.trim(),
    };

    if (isNaN(dados_funcionario.salario) || dados_funcionario.salario <= 0) {
        alert('Salário inválido. Insira um valor numérico positivo.');
        return;
    }

    // Make the POST request
    fetch('http://localhost:8080/funcionario', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados_funcionario),
    })
        .then(response => {
            // Handle HTTP errors
            if (!response.ok) {
                return response.json().then(error => {
                    throw new Error(error.message || 'Erro ao cadastrar funcionário.');
                });
            }
            return response.json();
        })
        .then(data => {
            // Success message and logging
            alert('Funcionário cadastrado com sucesso!');
            console.log('Resposta do servidor:', data);
        })
}

function recuperarDadosPorCPF_funcionario(cpf) {
    fetch(`http://localhost:8080/funcionario/cpf?valor=${cpf}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Funcionário não encontrado');
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
                        <td>${u.pessoa.telefone || 'Não disponível'}</td>
                        <td>R$${u.salario || 'Não disponivel'}</td>
                        <td>${u.horario_entrada || 'Não disponível'}</td>
                        <td>${u.horario_saida|| 'Não disponível'} </td>
                        <td>
                            <button class="table-button" onclick="editarDadosCPF_funcionario(${u.cpf})">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="deletarDadosFuncionarioCPF('${u.cpf}')">
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

function deletarDadosFuncionarioCPF(cpf) {
    fetch(`http://localhost:8080/funcionario/cpf?valor=${cpf}`, {
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
            getCPF_funcionario();
        })
        .catch(error => {
            alert('Não foi possível deletar. Usuário possui associação como Cliente ou Funcionário' );
        });
}

function editarDadosCPF_funcionario(cpf) {
    const userData = JSON.parse(localStorage.getItem('userData'));
    fetch(`http://localhost:8080/funcionario/cpf?valor=${cpf}`)
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
            window.location.href = '/editar_funcionario';
        })
        .catch(error => {
            alert('Erro ao buscar dados: ' + error.message);
            console.log(userData)
        });
}

function editarDados_funcionario(cpf) {
    const userData = JSON.parse(localStorage.getItem('userData'));
    if (!userData) {
        alert('Dados do usuário não encontrados.');
        return;
    }

    const campo = document.getElementById('classe_valor').value;
    const valor = document.getElementById('valor_alterado').value;
    console.log(userData)

    const data = { campo, valor };

    fetch(`http://localhost:8080/funcionario/cpf?valor=${userData.cpf}`, {
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
            console.log(userData.cpf)
        })
        .catch(error => {
            alert('Erro ao atualizar dados: ' + error.message);
        });
}

function get_mesa() {
    const id_mesa = document.getElementById('id-mesa').value;
    const filtro = document.getElementById('campo_pesquisa').value
    recuperarMesaPorId(id_mesa)
}

function registrar_mesa() {
    const dados_pessoa = {
        numero_id: document.getElementById('identificador_mesa').value,
        quantidade_cadeiras: document.getElementById('quantidade_cadeiras').value,
    };

    fetch('http://localhost:8080/mesa', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dados_pessoa)
    })
        .then(response => {
            if (!response.ok) {
                alert('ID já cadastrado no sistema');
            }
        })
        .then(data => {
            alert('Dados enviados com sucesso!');
            console.log(data);
        })
}

function recuperarMesaPorId(id_mesa) {
    fetch(`http://localhost:8080/mesa`)
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
                console.log(u)
                const row = `
                    <tr class="text">
                        <td>${u.numero_id || 'Não disponível'}</td>
                        <td>${u.quantidade_cadeiras || 'Não disponível'}</td>
                        <td>
                            <button class="table-button"" onclick="editarDadosEmail('${u.email}')">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="removerMesaId('${u.numero_id}')">
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

function removerMesaId(id_mesa){
    fetch(`http://localhost:8080/mesa/${id_mesa}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao deletar Mesa');
            }
            alert('Mesa excluida!');
            get_mesa();
        })
        .catch(error => {
            alert('Não foi possível deletar. Usuário possui associação como Cliente ou Funcionário' );
        });
}

function getCPF_garcom() {
    const cpf = document.getElementById('cpf_get_garcom').value;
    const filtro = document.getElementById('campo_pesquisa').value
    recuperarDadosPorCPF_garcom(cpf);
}

function cadastrar_garcom() {
    const dados_garcom = {
        cpf: document.getElementById('cpf').value.trim(),
        cpf_gerente: document.getElementById('cpf_gerente').value.trim() || null,
        mesas_atendidas: document.getElementById('mesas_atendidas').value
            .split(',')
            .map(mesa => parseInt(mesa.trim()))
            .filter(mesa => !isNaN(mesa)) // Remove valores inválidos
    };

    console.log(dados_garcom);

    // Make the POST request
    fetch('http://localhost:8080/garcom', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados_garcom),
    })
        .then(response => {
            // Handle HTTP errors
            if (!response.ok) {
                return response.json().then(error => {
                    throw new Error(error.message || 'Erro ao cadastrar funcionário.');
                });
            }
            return response.json();
        })
        .then(data => {
            // Success message and logging
            alert('Funcionário cadastrado com sucesso!');
            console.log('Resposta do servidor:', data);
        })
}

function recuperarDadosPorCPF_garcom(cpf) {
    fetch(`http://localhost:8080/garcom/cpf?valor=${cpf}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Funcionário não encontrado');
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
                        <td>${u.funcionario.pessoa.nome || 'Não disponível'}</td>
                        <td>${u.cpf || 'Não disponível'}</td>
                        <td>${u.funcionario.pessoa.telefone || 'Não disponível'}</td>
                        <td>${u.funcionario.horario_entrada || 'Não disponível'}</td>
                        <td>${u.funcionario.horario_saida|| 'Não disponível'} </td>
                        <td>R$${u.funcionario.salario || 'Não disponivel'}</td>
                        <td>${u.cpf_gerente || 'Gerente' }</td>
                        <td>
                            <button class="table-button" onclick="editarDadosCPF_garcom(${u.cpf})">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="deletarDadosGarcomCPF('${u.cpf}')">
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

function deletarDadosGarcomCPF(cpf) {
    fetch(`http://localhost:8080/garcom/cpf?valor=${cpf}`, {
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
            getCPF_garcom();
        })
        .catch(error => {
            alert('Não foi possível deletar. Usuário possui associação como Cliente ou Funcionário' );
        });
}

function editarDadosCPF_garcom(cpf) {
    const userData = JSON.parse(localStorage.getItem('userData'));
    fetch(`http://localhost:8080/garcom/cpf?valor=${cpf}`)
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
            window.location.href = '/editar_garcom';
        })
        .catch(error => {
            alert('Erro ao buscar dados: ' + error.message);
            console.log(userData)
        });
}

function editarDados_garcom(cpf) {
    const userData = JSON.parse(localStorage.getItem('userData'));
    if (!userData) {
        alert('Dados do usuário não encontrados.');
        return;
    }
    cpf = userData.cpf
    const mesas = document.getElementById('mesas').value
        .split(',')
        .map(mesa => parseInt(mesa.trim()))
        .filter(mesa => !isNaN(mesa)) // Remove valores inválidos
    console.log(userData)

    const data2 = {
        cpf, mesas
    }

    fetch(`http://localhost:8080/garcom/mesas`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data2)
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

function getID_estoque() {
    const estado = document.getElementById('get_estado').value;
    const filtro = document.getElementById('campo_pesquisa').value
    recuperarEstoquePorEstado(estado);
}

function cadastrar_estoque() {
    const dados_estoque = {
        id: parseInt(document.getElementById('id').value.trim()),
        rua: document.getElementById('rua').value.trim(),
        numero: parseInt(document.getElementById('numero').value.trim()),
        bairro: document.getElementById('bairro').value.trim(),
        estado: document.getElementById('Estado').value.trim(),
        cidade: document.getElementById('Cidade').value.trim(),
        cep: document.getElementById('CEP').value.trim(),
        refrigerado: document.getElementById('Refrigerado').checked // Captura valor booleano
    };

    console.log(dados_estoque);

    // Make the POST request
    fetch('http://localhost:8080/estoque', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados_estoque),
    })
        .then(response => {
            // Handle HTTP errors
            if (!response.ok) {
                return response.json().then(error => {
                    throw new Error(error.message || 'Erro ao cadastrar funcionário.');
                });
            }
            return response.json();
        })
        .then(data => {
            // Success message and logging
            alert('Funcionário cadastrado com sucesso!');
            console.log('Resposta do servidor:', data);
        })
}

function recuperarEstoquePorEstado(estado) {
    fetch(`http://localhost:8080/estoque/estado?valor=${estado}`)
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
                console.log(u)
                const row = `
                    <tr class="text">
                        <td>${u.id || 'Não disponível'}</td>
                        <td>${u.rua || 'Não disponível'}</td>
                        <td>${u.numero || 'Não disponível'}</td>
                        <td>${u.bairro || 'Não disponível'}</td>
                        <td>${u.estado || 'Não disponível'}</td>
                        <td>${u.cidade || 'Não disponível'}</td>
                        <td>${u.cep || 'Não disponível'}</td>
                        <td>${u.refrigerado || 'Não disponível'}</td>
                        <td>
                            <button class="table-button"" onclick="editarDadosIdEstoque('${u.id}')">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="deletarPorId('${u.id}')">
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

function deletarPorId(id) {
    fetch(`http://localhost:8080/estoque/id?valor=${id}`, {
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
            getID_estoque();
        })
        .catch(error => {
            alert('Não foi possível deletar. Usuário possui associação como Cliente ou Funcionário' );
        });
}

function editarDadosIdEstoque(id) {
    const dadosAtuaisEstoque = JSON.parse(localStorage.getItem('DadosEstoque'));

    fetch(`http://localhost:8080/estoque/id?valor=${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar dados para edição.');
            }
            return response.json();
        })
        .then(data => {
            const dadosRecebidos = data[0]
            localStorage.setItem('DadosEstoque', JSON.stringify(dadosRecebidos));
            window.location.href = '/editar_estoque';
        })
        .catch(error => {
            alert('Erro ao buscar dados: ' + error.message);
            console.log(dadosAtuaisEstoque); // Exibe os dados atuais
        });
}

function editarDadosEstoque(id) {
    const DadosEstoque = JSON.parse(localStorage.getItem('DadosEstoque'));
    if (!DadosEstoque) {
        alert('Dados do usuário não encontrados.');
        return;
    }
    id = DadosEstoque.id
    const campo = document.getElementById('classe_valor').value;
    const valor = document.getElementById('valor_alterado').value;
    console.log(DadosEstoque)

    const data = {
        campo, valor
    }

    fetch(`http://localhost:8080/estoque/id?valor=${id}`, {
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

function getCPF_estoquista() {
    const cpf = document.getElementById('cpf_get_estoquista').value;
    const valor_procura = document.getElementById('valor-de-procura').value
    recuperarDadosPorCPF_estoquista(cpf, valor_procura);
}

function cadastrar_estoquista() {
    const dados_estoquista = {
        cpf: document.getElementById('cpf').value.trim(),
        cpf_gerente: document.getElementById('cpf_gerente').value.trim() || null,
        estoque_id: document.getElementById('estoque-id').value
     };

    console.log(dados_estoquista)

    // Make the POST request
    fetch('http://localhost:8080/estoquista', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados_estoquista),
    })
        .then(response => {
            // Handle HTTP errors
            if (!response.ok) {
                return response.json().then(error => {
                    throw new Error(error.message || 'Erro ao cadastrar Estoquista.');
                });
            }
            return response.json();
        })
        .then(data => {
            // Success message and logging
            alert('Estoquista cadastrado com sucesso!');
            console.log('Resposta do servidor:', data);
        })
}

function recuperarDadosPorCPF_estoquista(cpf, valor_procura) {
    if(valor_procura === 'cpf'){
        fetch(`http://localhost:8080/estoquista/cpf?valor=${cpf}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Estoquista não encontrado');
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
                        <td>${u.estoquista.funcionario.pessoa.nome || 'Não disponível'}</td>
                        <td>${u.cpf || 'Não disponível'}</td>
                        <td>${u.estoquista.funcionario.pessoa.telefone || 'Não disponível'}</td>
                        <td>${u.estoquista.funcionario.horario_entrada || 'Não disponível'}</td>
                        <td>${u.estoquista.funcionario.horario_saida|| 'Não disponível'} </td>
                        <td>R$${u.estoquista.funcionario.salario || 'Não disponivel'}</td>
                        <td>${u.cpf_gerente || 'Gerente' }</td>
                        <td>
                            <button class="table-button" onclick="editarDadosCPF_estoquista(${u.cpf})">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="deletarDadosEstoquistaCPF('${u.cpf}')">
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
    }else{
        fetch(`http://localhost:8080/estoquista/id?valor=${cpf}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Estoquista não encontrado');
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
                        <td>${u.estoquista.funcionario.pessoa.nome || 'Não disponível'}</td>
                        <td>${u.cpf || 'Não disponível'}</td>
                        <td>${u.estoquista.funcionario.pessoa.telefone || 'Não disponível'}</td>
                        <td>${u.estoquista.funcionario.horario_entrada || 'Não disponível'}</td>
                        <td>${u.estoquista.funcionario.horario_saida|| 'Não disponível'} </td>
                        <td>R$${u.estoquista.funcionario.salario || 'Não disponivel'}</td>
                        <td>${u.cpf_gerente || 'Gerente' }</td>
                        <td>
                            <button class="table-button" onclick="editarDadosCPF_estoquista(${u.cpf})">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="deletarDadosEstoquistaCPF('${u.cpf}')">
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

}

function deletarDadosEstoquistaCPF(cpf) {
    fetch(`http://localhost:8080/estoquista/cpf?valor=${cpf}`, {
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
            getCPF_garcom();
        })
        .catch(error => {
            alert('Não foi possível deletar. Usuário possui associação como Cliente ou Funcionário' );
        });
}

function editarDadosCPF_estoquista(cpf) {
    const userData = JSON.parse(localStorage.getItem('userData'));
    fetch(`http://localhost:8080/garcom/cpf?valor=${cpf}`)
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
            window.location.href = '/editar_garcom';
        })
        .catch(error => {
            alert('Erro ao buscar dados: ' + error.message);
            console.log(userData)
        });
}

function editarDados_estoquista(cpf) {
    const userData = JSON.parse(localStorage.getItem('userData'));
    if (!userData) {
        alert('Dados do usuário não encontrados.');
        return;
    }
    cpf = userData.cpf
    const mesas = document.getElementById('mesas').value
        .split(',')
        .map(mesa => parseInt(mesa.trim()))
        .filter(mesa => !isNaN(mesa)) // Remove valores inválidos
    console.log(userData)

    const data2 = {
        cpf, mesas
    }

    fetch(`http://localhost:8080/garcom/mesas`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data2)
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

function cadastrar_produto() {
    const dados_produto = {
        id: document.getElementById('id').value.trim(),
        nome: document.getElementById('nome').value.trim(),
        validade: document.getElementById('validade').value,
        quantidade: document.getElementById('quantidade').value.trim(),
        distribuidora: document.getElementById('distribuidora').value.trim(),
        estoques: document.getElementById('estoques').value
            .trim()
            .split(',')
            .map(id => parseInt(id.trim(), 10)),
        medida: document.getElementById('medida').value.trim(),
    };


    // Make the POST request
    fetch('http://localhost:8080/produto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados_produto),
    })
        .then(response => {
            // Handle HTTP errors
            if (!response.ok) {
                return response.json().then(error => {
                    throw new Error(error.message || 'Erro ao cadastrar Produto.');
                });
            }
            return response.json();
        })
        .then(data => {
            // Success message and logging
            alert('Estoquista cadastrado com sucesso!');
            console.log('Resposta do servidor:', data);
        })
}

function getIdProduto(){
    const distribuidora = document.getElementById('distribuidora-produto').value
    const filtro = document.getElementById('campo_pesquisa').value

    recuperarProdutoPorID(distribuidora)
}

function recuperarProdutoPorID(distribuidora) {
    fetch(`http://localhost:8080/produto/distribuidora?valor=${distribuidora}`)
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
                console.log(u)
                const row = `
                    <tr class="text">
                        <td>${u.id || 'Não disponível'}</td>
                        <td>${u.nome || 'Não disponível'}</td>
                        <td>${u.validade || 'Não disponível'}</td>
                        <td>${u.quantidade || 'Não disponível'}</td>
                        <td>${u.distribuidora || 'Não disponível'}</td>
                        <td>${u.estoques || 'Não disponível'}</td>
                        <td>${u.medida || 'Não disponível'}</td>
                        <td>
                            <button class="table-button"" onclick="editarDadosIdEstoque('${u.id}')">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="deletarProdutoPorId('${u.id}')">
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

function deletarProdutoPorId(id){
    fetch(`http://localhost:8080/produto/?=${id}`, {
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
            getIdProduto();
        })
        .catch(error => {
            alert('Não foi possível deletar. Usuário possui associação como Cliente ou Funcionário' );
        });
}

function editarDadosID_Produto(id) {
    const dadoProduto = JSON.parse(localStorage.getItem('dadoProduto'));
    fetch(`http://localhost:8080/produto/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar dados para edição.');
            }
            return response.json();
        })
        .then(data => {
            const dadoProduto = data[0] || {};
            if (!dadoProduto.cpf) {
                throw new Error('Dados não encontrados.');
            }
            localStorage.setItem('dadoProduto', JSON.stringify(dadoProduto));
            window.location.href = '/editar_produto';
        })
        .catch(error => {
            alert('Erro ao buscar dados: ' + error.message);
            console.log(dadoProduto)
        });
}

function editarDados_Produto(id) {
    const dadoProduto = JSON.parse(localStorage.getItem('dadoProduto'));
    if (!dadoProduto) {
        alert('Dados do usuário não encontrados.');
        return;
    }
    id = dadoProduto.id
    const produto = document.getElementById('produto').value
        .split(',')
        .map(mesa => parseInt(mesa.trim()))
        .filter(mesa => !isNaN(mesa)) // Remove valores inválidos
    console.log(dadoProduto)

    const data2 = {
        cpf, mesas
    }

    fetch(`http://localhost:8080/garcom/mesas`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data2)
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

function getReserva() {
    const cpf = document.getElementById('cpf_get_cliente').value;
    const valor_procura = document.getElementById('data').value
    recuperarReserva(cpf, valor_procura);
}

function cadastrar_reserva(){
    const dados_reserva = {
        cpf_cliente: document.getElementById('cpf_cliente').value.trim(),
        data: document.getElementById('data').value.trim(),
        horario_entrada: document.getElementById('horario_entrada').value.trim(),
        quantidade_pessoas: document.getElementById('quantidade_pessoas').value.trim(),
        numero_mesa: document.getElementById('numero_mesa').value.trim(),
    };

    // Make the POST request
    fetch(`http://localhost:8080/reserva`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados_reserva),
    })
        .then(response => {
            // Handle HTTP errors
            if (!response.ok) {
                return response.json().then(error => {
                    throw new Error(error.message || 'Erro ao cadastrar Produto.');
                });
            }
            return response.json();
        })
        .then(data => {
            // Success message and logging
            alert('Estoquista cadastrado com sucesso!');
            console.log('Resposta do servidor:', data);
        })
}

function recuperarReserva(cpf, valor_procura){
    if(cpf === ''){
        if(valor_procura === ''){
            fetch(`http://localhost:8080/reserva`)
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
                        console.log(u)
                        const row = `
                    <tr class="text">
                        <td>${u.cpf_cliente || 'Não disponível'}</td>
                        <td>${u.data || 'Não disponível'}</td>
                        <td>${u.horario_entrada || 'Não disponível'}</td>
                        <td>${u.quantidade_pessoas || 'Não disponível'}</td>
                        <td>${u.numero_mesa || 'Não disponível'}</td>
                        <td>
                            <button class="table-button"" onclick="editarDadosIdEstoque('${u.cpf_cliente}')">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="deletarReserva('${u.cpf_cliente}', ${u.data})">
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
        }else{
        fetch(`http://localhost:8080/reserva/data?valor=${valor_procura}`)
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
                    console.log(u)
                    const row = `
                    <tr class="text">
                        <td>${u.cpf_cliente || 'Não disponível'}</td>
                        <td>${u.data || 'Não disponível'}</td>
                        <td>${u.horario_entrada || 'Não disponível'}</td>
                        <td>${u.quantidade_pessoas || 'Não disponível'}</td>
                        <td>${u.numero_mesa || 'Não disponível'}</td>
                        <td>
                            <button class="table-button"" onclick="editarDadosIdEstoque('${u.cpf_cliente}')">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="deletarReserva('${u.cpf_cliente}', ${u.data})">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="m696-440-56-56 83-84-83-83 56-57 84 84 83-84 57 57-84 83 84 84-57 56-83-83-84 83Zm-336-40q-66 0-113-47t-47-113q0-66 47-113t113-47q66 0 113 47t47 113q0 66-47 113t-113 47ZM40-160v-112q0-34 17.5-62.5T104-378q62-31 126-46.5T360-440q66 0 130 15.5T616-378q29 15 46.5 43.5T680-272v112H40Zm80-80h480v-32q0-11-5.5-20T580-306q-54-27-109-40.5T360-360q-56 0-111 13.5T140-306q-9 5-14.5 14t-5.5 20v32Zm240-320q33 0 56.5-23.5T440-640q0-33-23.5-56.5T360-720q-33 0-56.5 23.5T280-640q0 33 23.5 56.5T360-560Zm0-80Zm0 400Z"/></svg>
                            </button>
                        </td>
                    </tr>`;
                    tabela.insertAdjacentHTML('beforeend', row);
                });
            })
            .catch(error => {
                alert('Erro ao buscar dados: ' + error.message);
            });}
    }else{
        fetch(`http://localhost:8080/reserva/${cpf}/data?data=${valor_procura}`)
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
                    console.log(u)
                    const row = `
                    <tr class="text">
                        <td>${u.cpf_cliente || 'Não disponível'}</td>
                        <td>${u.data || 'Não disponível'}</td>
                        <td>${u.horario_entrada || 'Não disponível'}</td>
                        <td>${u.quantidade_pessoas || 'Não disponível'}</td>
                        <td>${u.numero_mesa || 'Não disponível'}</td>
                        <td>
                            <button class="table-button"" onclick="editarDadosIdEstoque('${u.cpf_cliente}')">
                            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="undefined"><path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/></svg>                            
                            </button>
                        </td>
                        <td>
                            <button class="table-button" onclick="deletarReserva('${u.cpf_cliente}', ${u.data})">
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
}

function deletarReserva(cpf, data){
    const dados  = fetch(`http://localhost:8080/reserva/${cpf}/data?data=${data}`)
        .then(response => {
        // Verifica se a resposta foi bem-sucedida
        if (!response.ok) {
            return response.json().then(error => {
                throw new Error(error.message || 'Erro ao obter os dados da reserva.');
            });
        }
        return response.json(); // Converte a resposta para JSON
    })

    console.log("Olha o log dos dados")
    console.log(dados)

    fetch(`http://localhost:8080/reserva`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify(dados)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao deletar usuário');
            }
            alert('Usuário deletado com sucesso!');
            getIdProduto();
        })
        .catch(error => {
            alert('Não foi possível deletar. Usuário possui associação como Cliente ou Funcionário' );
        });
}