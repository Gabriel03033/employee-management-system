const confirmDeleteButton = document.querySelector("#confirm-delete-button")
const cancelDeleteButton = document.querySelector("#cancel-delete-button")
const deleteButtons = document.querySelectorAll(".delete-button");
const modal = document.querySelector(".confirm-delete-modal")

let backdrop = null;

for(let index = 0; index < deleteButtons.length; index++) {
    deleteButtons[index].addEventListener("click", () => showModalHandler(event, index));
}

function showModalHandler(event, index) {
 event.preventDefault();
        const deleteButtonHref = deleteButtons[index].href;
        const employeeId = parseInt(deleteButtonHref.substring(deleteButtonHref.lastIndexOf("/") + 1));
        console.log("confirm deleting employee with id " + employeeId);

        confirmDeleteButton.addEventListener("click", () => confirmDeleteEmployeeById(employeeId));
        cancelDeleteButton.addEventListener("click", closeModalHandler);

        modal.classList.remove("d-none");

        backdrop = document.createElement("div");
        backdrop.className = "backdrop";
        backdrop.addEventListener("click", closeModalHandler);

        document.body.append(backdrop);
}

function confirmDeleteEmployeeById(employeeId) {
        fetch('http://localhost:8080/api/employees/' + employeeId, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                },
                body: null
            }).then(response => response.json()).then(data => window.location.reload());
        closeModalHandler();
}

function closeModalHandler() {
    modal.classList.add("d-none");
    backdrop.remove();
    backdrop = null;
}
