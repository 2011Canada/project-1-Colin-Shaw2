async function loginSubmit(event){
    
    event.preventDefault();
    
    let username = document.getElementById("username").value
    let password = document.getElementById("password").value
    
    const credentials = {
        username,
        password
    }
    
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

        if(user.role === "EMPLOYEE"){
            document.location.href = "employeeHomeScreen.html"
        }else if(user.role === "FINANCE_MANAGER"){
            document.location.href = "financeManager.html"
        }else{
            console.log("error")
        }
    } catch (e) {
        console.log(e);
    }
    
}

document.getElementById("loginForm").addEventListener('submit', loginSubmit);