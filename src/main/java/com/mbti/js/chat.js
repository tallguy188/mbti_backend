const stompClient = Stomp.over(new SockJS('/chatting'));

// 웹 소켓 연결
stompClient.connect({}, function(frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/sub/chat/{chatId}', function(response) {
        const message = JSON.parse(response.body);
        showMessage(message);
    });
});

// 메시지 보내기
function sendMessage() {
    const chatId = document.getElementById('chatId').value;
    const message = document.getElementById('message').value;
    stompClient.send('/pub/chat/' + chatId, {}, JSON.stringify({
        'message': message
    }));
}

// 채팅방 생성하기
function createChatRoom() {
    const chatId = document.getElementById('chatId').value;
    const userIds = [1, 2]; // 사용자 ID
    stompClient.send('/pub/chat/create', {}, JSON.stringify({
        'chatId': chatId,
        'userIds': userIds
    }));
}

// 메시지 보여주기
function showMessage(message) {
    const chatRoomDiv = document.getElementById('chatRoom');
    const p = document.createElement('p');
    p.innerHTML = message.sender + ': ' + message.message;
    chatRoomDiv.appendChild(p);
}

// 사용자 목록 보여주기
function showUserList(userList) {
    const userListDiv = document.getElementById('userList');
    for (let i = 0; i < userList.length; i++) {
        const p = document.createElement('p');
        p.innerHTML = userList[i].name;
        userListDiv.appendChild(p);
    }
}