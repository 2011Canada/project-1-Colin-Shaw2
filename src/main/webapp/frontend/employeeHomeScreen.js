
async function submitReimbursement(event){
    event.preventDefault()
    let amount =document.getElementById("amountInput").value
    let type = document.getElementById("expenseTypeSelect").value
    let description = document.getElementById("descriptionInput").value
    
    const reimbursement = {
        amount,
        type,
        description
    }
    console.log(reimbursement)
    
    try{
        let res = await fetch("http://localhost:8080/p1colin/reimbursements/submit", {
            method:"POST",
            body: JSON.stringify(reimbursement),
            headers:{
                "Content-Type" : "application/json"
            }
        })
        
        let reimbursement = await res.json()
        console.log(reimbursement);

    } catch (e) {
        console.log(e);
    }
}

async function getAllTickets(event){
    event.preventDefault()
    
    try{
        let res = await fetch("http://localhost:8080/p1colin/reimbursements/user", {
            method:"GET",
            headers:{
                "Content-Type" : "application/json"
            },
        })
        
        let reimbursements = await res.json()
        console.log(reimbursements);
        

        reimbursements.forEach(element => {
            let lu = document.createElement('lu')
            lu.innerText = element.amount
            lu.className = "list-group-item"
            document.getElementById("reimbursementList").appendChild(lu)
        });
    } catch (e) {
        console.log(e);
    }
}


document.getElementById("reimbursementForm").addEventListener("submit", submitReimbursement)


document.getElementById("getTickets").addEventListener("click", getAllTickets)

