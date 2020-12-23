async function getTransactionsForEmployee(employee){
    
}

async function getCurrentUser(username, password){

}


async function sendNewReimbursementRequest(reimbursement){
}

function submitReimbursement(){
    let amount =document.getElementById("amountInput").value
    let expenseType = document.getElementById("expenseTypeSelect").value
    let description = document.getElementById("descriptionInput").value
    
    let reimbursement = {
        amount,
        expenseType,
        description
    }
    console.log(reimbursement)
}

let currentUser = getCurrentUser()


// let listOfTransactions = getTransactionsForEmployee(currentEmployee)

let reimbursementForm = document.getElementById("reimbursementForm")

reimbursementForm.addEventListener("submit", (event) =>{
    event.preventDefault()
    submitReimbursement()
}
)