
async function submitReimbursement(event){
    event.preventDefault()
    let amount =document.getElementById("amountInput").value
    let expenseType = document.getElementById("expenseTypeSelect").value
    let description = document.getElementById("descriptionInput").value
    
    const reimbursement = {
        amount,
        expenseType,
        description
    }
    console.log(reimbursement)
    
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

async function getAllTickets(event){
    event.preventDefault()
    // let username = document.getElementById("username").value
    // let password = document.getElementById("password").value
    
    // const credentials = {
    //     username,
    //     password
    // }
    
    // console.log(reimbursement)
    
    try{
        let res = await fetch("http://localhost:8080/p1colin/reimbursements/user", {
            method:"GET",
            headers:{
                "Content-Type" : "application/json"
            }
        })
        
        let reimbursements = await res.json()
        console.log(reimbursements);

    } catch (e) {
        console.log(e);
    }
}


document.getElementById("reimbursementForm").addEventListener("submit", getAllTickets)
// getAllTickets()