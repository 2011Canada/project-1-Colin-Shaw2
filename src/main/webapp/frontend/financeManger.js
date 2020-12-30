async function getAllTicketsByStatus(event){
    event && event.preventDefault()
    
    let url = "http://localhost:8080/p1colin/manager/"
    url += document.getElementById("filterSelect").value
    console.log(url)
    try{
        let res = await fetch(url, {
            method:"GET",
            headers:{
                "Content-Type" : "application/json"
            },
        })
        
        let reimbursements = await res.json()
        console.log(reimbursements);
        

        document.getElementById("reimbursementList").innerHTML = ""
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

    listItem.className = `card card-${reimbursement.status.toLowerCase()} mb-4`
    listItem.dataset.id = reimbursement.id;

    (reimbursement.status == 'PENDING')?addButtons(listItem):'';

    parentElement.appendChild(listItem)
}


function addButtons(parent){
    parent.appendChild(document.createElement('br'))
    let approveBut = document.createElement('button')
    approveBut.className = "btn approve-button btn-success"
    approveBut.innerText = "Approve"

    approveBut.addEventListener("click", async (event) =>{
        event.preventDefault()
        console.log(event.target.parentElement)

        // console.log(reimbursement)
        let res = await fetch("http://localhost:8080/p1colin/manager/approvereq", {
            method:"POST",
            // body: `reimbursementid=1`,
            body: `reimbursementid=${event.target.parentElement.dataset.id}`,
            headers:{
                "Content-Type" : "application/json"
            },
        })

        let newReimbursements = await res.json()
        console.log(newReimbursements)
        }
    )

    parent.appendChild(approveBut)

    let declineBut = document.createElement('button')
    declineBut.className = "btn decline-button btn-danger"
    declineBut.innerText = "Decline"

    declineBut.addEventListener("click", async (event) =>{
        event.preventDefault()
        console.log(event.target.parentElement)

        
        let res = await fetch("http://localhost:8080/p1colin/manager/denyreq", {
            method:"POST",
            body: `reimbursementid=${event.target.parentElement.dataset.id}`,
            headers:{
                "Content-Type" : "application/json"
            },
        })

        let newReimbursements = await res.json()
        console.log(newReimbursements)
        }
    )
    
    parent.appendChild(declineBut)
    
}
getAllTicketsByStatus()
document.getElementById("filterForm").addEventListener("submit", getAllTicketsByStatus)