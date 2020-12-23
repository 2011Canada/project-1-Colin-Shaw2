function getFormData(){
    username = document.getElementById("username")
    password = document.getElementById("password")

    let user = {
        username,
        password
    }
    console.log(user)
    
}

let loginForm = document.getElementById("loginForm")

loginForm.addEventListener("submit", (event) =>
    {
        event.preventDefault()
        getFormData()
    }
)

