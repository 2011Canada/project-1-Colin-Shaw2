// let loginButton = document.getElementById("btn-login")

// let loginCard = document.getElementById("login-form")

// console.log(loginCard)

// loginButton.addEventListener("click", () =>{
//     loginCard.parentElement.removeChild(loginCard);
//     }
// )

async function getTransactionsForEmployee(employee){
    
}

async function getCurrentUser(username, password){

}


async function sendNewReimbursementRequest(reimbursement){
}

function submitReimbursement(amount, expenseType, description){
    console.log(document.getElementById("amountInput").value)
    console.log(document.getElementById("expenseTypeSelect").value)
    console.log(document.getElementById("descriptionInput").value)
    alert(amount + expenseType + description)
}

let currentUser = getCurrentUser()


// let listOfTransactions = getTransactionsForEmployee(currentEmployee)

let submitReimbursementButton = document.getElementById("reimbursementForm")

submitReimbursementButton.addEventListener("submit",  submitReimbursement)

