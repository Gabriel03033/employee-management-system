const employees = document.querySelectorAll(".employee-row");
const deleteButtons = document.querySelectorAll(".delete-button");

let modal = null;
let backdrop = null;

for(let index = 0; index < deleteButtons.length; index++) {
    deleteButtons[index].addEventListener("click", function(event) {
        event.preventDefault();
        if (modal != null) return;
        const employeeRow = employees[index];
        const employeeId = parseInt(employeeRow.firstElementChild.innerText);
        console.log("deleting employee with id " + employeeId);

        modal = document.createElement("div");
        modal.className = "custom-modal";

        const modalText = document.createElement("p");
        modalText.textContent = "Are you sure you want to delete this employee?";

        const modalConfirmDeleteAction = document.createElement("button");
        modalConfirmDeleteAction.textContent = "Confirm";
        modalConfirmDeleteAction.className = "btn btn-success";
        modalConfirmDeleteAction.type = "button";
        modalConfirmDeleteAction.addEventListener("click", () => confirmDeleteEmployeeById(event, employeeId,employeeRow));

        const modalCancelAction = document.createElement("button");
        modalCancelAction.textContent = "Cancel";
        modalCancelAction.className = "btn btn-danger ml-3";
        modalCancelAction.type = "button";
        modalCancelAction.addEventListener("click", closeModalHandler);

        modal.append(modalText);
        modal.append(modalConfirmDeleteAction);
        modal.append(modalCancelAction);

        document.body.append(modal);

        backdrop = document.createElement("div");
        backdrop.className = "backdrop";
        backdrop.addEventListener("click", closeModalHandler);

        document.body.append(backdrop);
    });
}

function confirmDeleteEmployeeById(event, employeeId, employeeRow) {
        event.preventDefault();
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
    modal.remove();
    modal = null;
    backdrop.remove();
    backdrop = null;
}
