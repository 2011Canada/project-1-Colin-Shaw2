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
        

        reimbursements.forEach(element => {
            let li = document.createElement('li')
            li.innerText = element.amount
            li.className = "list-group-item"
            li.dataset.id = element.id

            //add style
            let approveBut = document.createElement('button')
            approveBut.className = "approve-button"
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

            li.appendChild(approveBut)


            //add style
            let declineBut = document.createElement('button')
            declineBut.className = "decline-button"
            declineBut.innerText = "Decline"

            declineBut.addEventListener("click", async (event) =>{
                event.preventDefault()
                console.log(event.target.parentElement)

                // console.log(reimbursement)
                let res = await fetch("http://localhost:8080/p1colin/manager/declinereq", {
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
            
            li.appendChild(declineBut)

            document.getElementById("reimbursementList").appendChild(li)
        });
    } catch (e) {
        console.log(e);
    }
}

getAllTicketsByStatus()
document.getElementById("filterForm").addEventListener("submit", getAllTicketsByStatus)