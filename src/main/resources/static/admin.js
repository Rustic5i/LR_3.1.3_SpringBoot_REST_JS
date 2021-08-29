///////////////////////////////// Новый User/////////////////////////////////////
function addUser() {
    let user = {
        firstName: $('#InputFirstName').val(),
        lastName: $('#InputLastName').val(),
        age: $('#InputAge').val(),
        email: $('#exampleInputEmail1').val(),
        password: $('#exampleInputPassword1').val(),
        roles: $('#listRole').val()
    }
    const url = '/api/admin'
    const method = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(user)
    }
    fetch(url,method)
}




////////////////////////////// Table Admin///////////////////////////////////////////////////////////////////////////////
async function tableAdmin() {
    const url = '/api/admin'
    const container = $('#adminTable');
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)

        let html = '';
        let roleText = ''
        response.forEach(user=>{
            for (const rolesTextElement of user.roles) {
                roleText = roleText + rolesTextElement.substring(5)+' '
            }
            let trHtml =
                `<tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${roleText}</td>
                      <td>
                                        <button id="btnEditUser" onclick="fillModelEditUserById(${user.id})" type="button" class="btn btn-primary" data-bs-toggle="modal">
                                            Edit
                                        </button>
                                    </td>
                                    <td>
                                        <button id="btnDeleteUser" onclick="fillModelDeletUserById(${user.id})" type="button" class="btn bg-danger text-white" data-bs-toggle="modal">
                                            Delete
                                        </button>
                </tr>`;
            html += trHtml;
            container.html(html);
            roleText = ''
        })
    })
}
tableAdmin()

//////////////////////////////////////////DELETE USER///////////////////////////////////////////////////////////////////////////////////////
// Заполнение значением модальное окно удаления узера
function fillModelDeletUserById(id){
    const urluserByid = '/api/admin/'+id
    const method = {
        dataType: 'json',
        type: 'GET'
    }
    fetch(urluserByid,method).then(response => response.json()).then(user => {
        console.log(user)
        $('#DeleteId').val(`${user.id}`)
        $('#DeleteFirstName').val(`${user.firstName}`)
        $('#DeleteLastName').val(`${user.lastName}`)
        $('#DeleteAge').val(`${user.age}`)
        $('#DeleteEmail').val(`${user.email}`)
        viewOpenModalDeleteUser()
    })
}

// Delet User by Id
function deletUserById(id){
    const url = '/api/admin/'+id
    const method = {
        method : 'DELETE',
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        }
    }
    fetch(url,method).then(()=>{tableAdmin()})
}
////////////
function viewOpenModalDeleteUser(){  //Открыть модальное окно
    const viewHtmlDelete = document.getElementById('myModal')
    viewHtmlDelete.style.display = "block"  //Запуск модалки
    console.log('TEEEEEEEEST',viewHtmlDelete)
}
///Закрыть модальное окно удаления юзера на нажатие кнопки
function closeModalDelete(){
    const viewHtmlDelete = document.getElementById('myModal')
    viewHtmlDelete.style.display = "none" // закрыть окно при нажатие кнопки крестик и на кнопку Close
}


///////////////////////////////////////////UPDATE USER////////////////////////////////////////////////////////
function fillModelEditUserById(id){
    const urluserByid = '/api/admin/'+id
    const method = {
        dataType: 'json',
        type: 'GET'
    }
    fetch(urluserByid,method).then(response => response.json()).then(user => {
        console.log(user)
        $('#id0').val(`${user.id}`)
        $('#UpdateFirstName').val(`${user.firstName}`)
        $('#UpdateLastName').val(`${user.lastName}`)
        $('#UpdateAge').val(`${user.age}`)
        $('#UpdateEmail').val(`${user.email}`)
        viewOpenModalEditUser()
    })
}

////////////
function viewOpenModalEditUser(){  //Открыть модальное окно
    const viewHtmlDelete = document.getElementById('editModal')
    viewHtmlDelete.style.display = "block"  //Запуск модалки
    console.log('TEEEEEEEEST',viewHtmlDelete)
}

///Закрыть модальное окно обновления юзера на нажатие кнопки
function closeModalEdit(){
    const viewHtmlDelete = document.getElementById('editModal')
    viewHtmlDelete.style.display = "none" // закрыть окно при нажатие кнопки крестик и на кнопку Close
}
// Delet User by Id
function updateUser(){
    let user = {
        id: $('#id0').val(),
        firstName: $('#UpdateFirstName').val(),
        lastName: $('#UpdateLastName').val(),
        age: $('#UpdateAge').val(),
        email: $('#UpdateEmail').val(),
        password: $('#UpdatePassword').val(),
        roles: $('#UpdateListRoles').val()
    }
    const url = '/api/admin'
    const method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(user)
    }
    fetch(url,method)
    tableAdmin()
}






