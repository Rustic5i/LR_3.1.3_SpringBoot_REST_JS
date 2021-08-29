// header User
function header() {
    const url = '/api/user'

    fetch(url).then(response => response.json()).then(user => {
        let rolestext = ''
        for (const rolestextElement of user.roles) {
            rolestext = rolestext + rolestextElement.substring(5)+' ';
            console.log(rolestext)
        }
        const container = $('#navbarEmail').text(`${user.email}`)
        const containerRole = $('#navbarRole').text(rolestext)
    })


}
header()

// Table User

async function tableUser() {
    const url = '/api/user'
    const container = $('#userid');
    $.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
    }).done((response) => {
        console.log(response)

        let html = '';
        let roleText = ''
            for (const rolesTextElement of response.roles) {
                roleText = roleText + rolesTextElement.substring(5)+' '
            }
        let trHtml =
            `<tr>
                    <td>${response.id}</td>
                    <td>${response.firstName}</td>
                    <td>${response.lastName}</td>
                    <td>${response.age}</td>
                    <td>${response.email}</td>
                    <td>${roleText}</td>
                </tr>`;
        html += trHtml;
        container.html(html);
    })
}

tableUser()