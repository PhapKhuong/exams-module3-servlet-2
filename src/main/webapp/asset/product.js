var delProductModal = document.getElementById("delProductModal");
delProductModal.addEventListener("show.bs.modal", function (event) {

    var button = event.relatedTarget;

    var id = button.getAttribute("data-bs-id");

    var modalBodyInputId = delProductModal.querySelector("#id");

    modalBodyInputId.value = id;
});

var editProductModal = document.getElementById("editProductModal");
editProductModal.addEventListener("show.bs.modal", function (event) {
    var button = event.relatedTarget;

    var id = button.getAttribute("data-bs-id");
    var name = button.getAttribute("data-bs-name");
    var price = button.getAttribute("data-bs-price");
    var quantity = button.getAttribute("data-bs-quantity");
    var color = button.getAttribute("data-bs-color");
    var description = button.getAttribute("data-bs-description");

    var modalBodyInputId = editProductModal.querySelector("#newId");
    var modalBodyInputName = editProductModal.querySelector("#newName");
    var modalBodyInputPrice = editProductModal.querySelector("#newPrice");
    var modalBodyInputQuantity = editProductModal.querySelector("#newQuantity");
    var modalBodyInputColor = editProductModal.querySelector("#newColor");
    var modalBodyInputDescription = editProductModal.querySelector("#newDescription");
    var modalBodyInputDescription = editProductModal.querySelector("#newDescription");
    const cateId = document.getElementById("testbt").getAttribute("data-bs-cId");

    alert(cateId);
    newCategory.value = cateId;

    modalBodyInputId.value = id;
    modalBodyInputName.value = name;
    modalBodyInputPrice.value = price;
    modalBodyInputQuantity.value = quantity;
    modalBodyInputColor.value = color;
    modalBodyInputDescription.value = description;
});