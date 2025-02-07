console.log("Delete Contact");

var id = "0";
function changeId(currentId) {
  id = currentId;
}
function deleteContact() {
  window.location.href = `http://localhost:8080/user/contacts/delete/${id}`;
}
