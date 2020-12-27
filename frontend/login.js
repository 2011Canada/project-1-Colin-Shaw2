const xhttp = new XMLHttpRequest()
const url = 'https://localhost:8080/project1Colin'

// function getFormData(){
//     username = document.getElementById("username")
//     password = document.getElementById("password")

//     let user = {
//         username,
//         password
//     }
//     console.log(user)
    
//     xhttp.open("POST", url);
//     xhttp.send(JSON.stringify(user))
//     console.log(xhttp.responseText)
// }

// let loginForm = document.getElementById("loginForm")

// loginForm.addEventListener("submit", (event) =>
//     {
//         event.preventDefault()
//         getFormData()
//     }
// )

async function loginSubmit(event){
    
    event.preventDefault();
    
    let username = document.getElementById("username").value
    let password = document.getElementById("password").value
    
    console.log(username)
    console.log(password)

    const credentials = {
        username,
        password
    }
    console.log(`Credentials are ${credentials.password}`)
    
    try{
        let res = await fetch("http://localhost:8080/p1colin/login", {
            method:"POST",
            body: JSON.stringify(credentials),
            headers:{
                "Content-Type" : "application/json"
            }
        })
        
        let user = await res.json()
        console.log(user);

        document.location.href = "financeManager.html"
    } catch (e) {
        console.log(e);
    }
    
}

document.getElementById("loginForm").addEventListener('submit', loginSubmit);