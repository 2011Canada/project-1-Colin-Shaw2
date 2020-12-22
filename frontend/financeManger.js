async function getAllTransactions() {}

async function getAllTransactionsWithStaus(status) {
  if (status !== "PENDING" || status !== "APPROVED" || status !== "DENIED") {
    throw `incorrect status passed in :${status}
            Expected "PENDING", "APPROVED", "DENIED"`;
  }

}

async function getCurrentUser(username, password) {}

async function approveReimbursement(reimbursement) {}

async function denyReimbursement(reimbursement) {}

let currentUser = getCurrentUser()

let listOfTransactions = queryTransactionsForEmployee(currentEmployee)
