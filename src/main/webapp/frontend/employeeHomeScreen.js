
async function submitReimbursement(event){
    event.preventDefault()
    let amount =document.getElementById("amountInput").value
    let type = document.getElementById("expenseTypeSelect").value
    let description = document.getElementById("descriptionInput").value
    
    let newReimbursement = {
        amount,
        type,
        description
    }
    console.log(newReimbursement)
    
    try{
        let res = await fetch("http://localhost:8080/p1colin/reimbursements/submit", {
            method:"POST",
            body: JSON.stringify(newReimbursement),
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

        document.getElementById("ticketList").innerHTML = ""
        
        let cardHeader = document.createElement("div");
        cardHeader.className = "card-header"
        cardHeader.innerText = "Previous Tickets"
        document.getElementById("ticketList").appendChild(cardHeader)
        
        let ul = document.createElement("ul");
        ul.className = "list-group list-group-flush"
        ul.id = "reimbursementList"
        document.getElementById("ticketList").appendChild(ul)
        

        reimbursements.forEach(element => {
            createReimbursementRow(element, document.getElementById("reimbursementList"))
        });
    } catch (e) {
        console.log(e);
    }
}


function createReimbursementRow(reimbursement, parentElement){
    let listItem = document.createElement('div')
    let statusString = (reimbursement.status == 'PENDING')?'':` on ${new Date(reimbursement.resolved).toLocaleDateString("en-US")}`
    
    listItem.innerText = `Submitted on ${new Date(reimbursement.submitted).toLocaleDateString("en-US")}
    $${reimbursement.amount} for ${reimbursement.type.toLowerCase()}
    ${reimbursement.status.charAt(0).toUpperCase() + reimbursement.status.slice(1).toLowerCase()}${statusString}
    Desc: ${reimbursement.description}`

    listItem.className = `card card-${reimbursement.status.toLowerCase()}`
    parentElement.appendChild(listItem)
}

document.getElementById("reimbursementForm").addEventListener("submit", submitReimbursement)


document.getElementById("getTickets").addEventListener("click", getAllTickets)

