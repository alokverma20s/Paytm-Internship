console.log("Delete Contact");

var id = "0";
function changeId(currentId) {
  id = currentId;
}
function deleteContact() {
  window.location.href = `${baseUrl}/user/contacts/delete/${id}`;
}
