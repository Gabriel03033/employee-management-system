const deleteButtons = document.querySelectorAll(".delete-button");
console.log(deleteButtons);
const numberOfDeleteButtons = deleteButtons.length;
console.log(numberOfDeleteButtons);

let modal = null;
let backdrop = null;

for(let index = 0; index < numberOfDeleteButtons; index++) {
    deleteButtons[index].addEventListener("click", function(index) {
        console.log("deleting button #" + (index + 1));

        modal = document.createElement("div");
        modal.className = "modal";

        const modalText = document.createElement("p");
        modalText.textContent = "Are you sure you want to delete this employee?";

        const modalConfirmAction = document.createElement("a");
        modalConfirmAction.textContent = "Confirm";
        modalConfirmAction.className = "btn btn-success";
        modalConfirmAction.href = "";
        modalConfirmAction.addEventListener("click", () => confirmDeleteEmployee(index));

        const modalCancelAction = document.createElement("button");
        modalCancelAction.textContent = "Cancel";
        modalCancelAction.className = "btn btn-danger";
        modalCancelAction.addEventListener("click", closeModalHandler);

        modal.append(modalText);
        modal.append(modalConfirmAction);
        modal.append(modalCancelAction);

        backdrop = document.createElement("div");
        backdrop.className = "backdrop";
        backdrop.addEventListener("click", closeModalHandler);

        document.body.append(modal);
        document.body.append(backdrop);
    });
}

function confirmDeleteEmployee(id) {
    const pressedDeleteButton = deleteButtons[id];
    const deleteButtonHref = pressedDeleteButton.href;
    this.href = deleteButtonHref;
    closeModalHandler();
}

function closeModalHandler() {
    modal.remove();
    modal = null;
    backdrop.remove();
    backdrop = null;
}
