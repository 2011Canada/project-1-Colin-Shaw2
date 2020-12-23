
async function getAllTransactionsWithStatus(status) {
  if (status !== "PENDING" || status !== "APPROVED" || status !== "DENIED") {
    throw `incorrect status passed in :${status}
    Expected "PENDING", "APPROVED", "DENIED"`;
  }
}
async function getAllTransactions() {}


let filterForm = document.getElementById("filterForm")

filterForm.addEventListener("submit", (event) =>{
  event.preventDefault()
  let status = document.getElementById("filterSelect")
  console.log(status)
  }
  )