console.log("Contacts.js");
const viewContactModal = document.getElementById("view_contact_modal");
const baseUrl = "http://localhost:8080"

// options with default values
const options = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => {
    console.log("modal is hidden");
  },
  onShow: () => {
    console.log("modal is shown");
  },
  onToggle: () => {
    console.log("modal has been toggled");
  },
};

// instance options object
const instanceOptions = {
  id: "view_contact_mdoal",
  override: true,
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal() {
  contactModal.show();
}

function closeContactModal() {
  contactModal.hide();
}

async function loadContactdata(id) {
  //function call to load data
  console.log(id);
  try {
    const data = await (
      await fetch(`${baseUrl}/api/contacts/${id}`)
    ).json();
    console.log(data);
    document.querySelector("#contact_name").innerHTML = data.name;
    document.querySelector("#contact_email").innerHTML = data.email;
    document.getElementById("picture").src = data.picture;
    document.getElementById("name").innerText = data.name;
    document.getElementById("email").innerText = data.email;
    document.getElementById("phoneNumber").innerText = data.phoneNumber;
    document.getElementById("address").innerText = data.address;
    document.getElementById("description").innerText = data.description;
    document.getElementById("websiteLink").innerText = data.websiteLink;
    document.getElementById("websiteLink").href = data.websiteLink;
    document.getElementById("linkedInLink").innerText = data.linkedInLink;
    document.getElementById("linkedInLink").href = data.linkedInLink;
    document.getElementById("favorite").checked = data.favorite;
    
    openContactModal();
  } catch (error) {
    console.log("Error: ", error);
  }
}
