
function onLogin(){
    var data = {};
    data.username = document.getElementById("nameLogin").value;
    data.password = document.getElementById("passwordLogin").value;

    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        mimeType: 'application/json',
        async: true,
        url:"/login",
        data: JSON.stringify(data),
        success: function (result) {
            console.log(result);
            initQueue("/login",result);
        }
    });
}

function initQueue(url,result){
    switch (url) {
        case '/login':
            whenLogin(result);
            break;
        case '/addTask':
            showMyPage();
            break;
        default :
            break;
    }

}

function showMyPage(){
    document.getElementById("addTask").style.display = "none";
    document.getElementById("mypage").style.display = "block";
    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        mimeType: 'application/json',
        async: true,
        url:"/showAllTask",
        success: function (result) {
            console.log(result);
            showAllTask(result);
        }
    });
}

function whenLogin(result){
    if(result.username != null){
        document.getElementById("singUp").style.display = "none";
        document.getElementById("mypage").style.display = "block";
        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            mimeType: 'application/json',
            async: true,
            url:"/showAllTask",
            success: function (result) {
                console.log(result);
                showAllTask(result);
            }
        });

    }else{
       document.getElementById("nameLogin").value = "";
       document.getElementById("passwordLogin").value = "";
    }
}

function showAllTask(result){
    var html = '<table style="width: 100%; border-collapse: collapse;"><thead>\
            <tr>\
                <td>name</td>\
                <td>desc</td>\
                <td>status</td>\
                <td>isFinish</td>\
                <td>deadline</td>\
            </tr>\
            </thead>\
            <tbody id="renderList"></tbody>\
        </table>';
    $('#result').html(html);
    var html = '';
    for(var ii in result){
        html += '<tr>';
        html += '<td>';
        html += result[ii].name;
        html += '</td>';
        html += '<td>';

        html += result[ii].description;
        html += '</td>';
        html += '<td>';
        html += result[ii].status;
        html += '</td>';
        html += '<td>';

        html += result[ii].finish;
        html += '</td>';
        html += '<td>';

        html += result[ii].deadline;
        html += '</td>';
        html += '</tr>';
    }
    $('#renderList').html(html);
}


function addNewTask(){
    document.getElementById("addTask").style.display = "block";
    document.getElementById("mypage").style.display = "none";
}


function saveNewTask(){
    var data = {};
    data.name = document.getElementById("taskName").value;
    data.description = document.getElementById("taskDescriptiom").value;
    data.status = document.getElementById("taskStatus").value;
    data.isFinish = $("#taskFinish").checked;
    data.deadline = document.getElementById("taskDeadline").value;
    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        mimeType: 'application/json',
        async: true,
        url:"/addTask",
        data: JSON.stringify(data),
        success: function (result) {
            console.log(result);
            initQueue("/addTask",result);
        }
    });
}