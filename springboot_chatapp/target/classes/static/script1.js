var stompClient = null;

// Function to connect to the WebSocket server
function connect() {
    try {
        let socket = new SockJS("/server1"); // Makes the new SockJS object on the path /server1
        stompClient = Stomp.over(socket); // Runs Stomp over the socket

        // Actions taken upon connection
        stompClient.connect({}, function (frame) {
            console.log("Connection established: " + frame);

            // Update UI upon connection
            $("#name-form").addClass("d-none"); // Hide the name form
            $("#chat-room").removeClass("d-none"); // Show the chat room

            // Subscribe to the topic and handle messages
            stompClient.subscribe("/topic/return-to", function (response) {
                try {
                    showMessage(JSON.parse(response.body));
                } catch (error) {
                    console.error("Error processing message:", error);
                }
            });
        });
    } catch (error) {
        console.error("Connection failed:", error);
    }
}

// Function to display a message
function showMessage(message) {
    $("#message-container-table").append(
        `<tr><td><b>${message.name}: </b> ${message.content}</td></tr>`
    );
}

// Function to send a message
function sendMessage() {
    let jsonObj = {
        name: localStorage.getItem("name"),
        content: $("#message-value").val(), // Corrected selector with #
    };

    try {
        stompClient.send("/chatty/message", {}, JSON.stringify(jsonObj));
        $("#message-value").val(""); // Clear the input field after sending
    } catch (error) {
        console.error("Error sending message:", error);
    }
}

// Document ready actions
$(document).ready(() => {
    // Login button click action
    $("#login").click(() => {
        let name = $("#name-value").val(); // Get name from input field
        if (name) {
            localStorage.setItem("name", name);
            $("#name-title").html(`Welcome, <b>${name}</b>`); // Display the name
            connect(); // Connect to the WebSocket server
        } else {
            alert("Please enter a name to continue.");
        }
    });

    // Send button click action
    $("#send-btn").click(() => {
        sendMessage(); // Send the message
    });

    // Logout button click action
    $("#logout").click(() => {
        localStorage.removeItem("name"); // Clear name from local storage
        if (stompClient != null) {
            stompClient.disconnect(() => {
                console.log("Disconnected");
            });
        }

        // Update UI on logout
        $("#name-form").removeClass("d-none"); // Show the name form
        $("#chat-room").addClass("d-none"); // Hide the chat room
    });
});
