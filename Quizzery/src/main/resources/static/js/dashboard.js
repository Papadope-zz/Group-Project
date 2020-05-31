


const userQuizzes = document.getElementById("userQuizzes");


$('#createNewQuizz').click(function(e){
	e.preventDefault()
	  var quizzName = {
	    quizName: $("#newQuizName").val(),
		description:"desc",
		subject:$("#newSubject").val(),
		category:$("#newCategory").val(),
		difficulty:$("#newDifficulty").val(),
		type:"type"
	  };

var json= JSON.stringify(quizzName);
	console.log(json);
	$.ajax({
		  url: 'http://localhost:8080/Quizzery/quizzes',
		  type: 'POST',
		  dataType: 'json',
		   data: json,
		  contentType: 'application/json',
		  success: response => {
			  console.log(response);
			  window.location.href = "/Quizzery/dashboard"  
		  }
	});
});

const deleteQuiz =(quizId)=>{	
	console.log("deeteeee")
	$.ajax({
		  url: 'http://localhost:8080/Quizzery/quizzes/'+quizId,
		  type: 'DELETE',
		  dataType: 'json',
		   data: "",
		  contentType: 'application/json',
		  success: response => {
			  console.log(response);
			  window.location.href = "/Quizzery/dashboard"  
		  }	
   })
}



//creating users's Quizz Table dynamicaly

$.ajax({
  url: 'http://localhost:8080/Quizzery/quizzes',
  type: 'GET',
  dataType: 'json', 
  contentType: 'application/json',
  success: response => {
   console.log(response);
   quizzes = [];
   quizzes = response;
   
userQuizzes.innerHTML = "";
let innerString ="" 

response.map(function(quizz) {
 let dateCreated=quizz.dateCreated.substring(0,10);
 innerString += 
	  
             ` <tr>
<th scope="row"> ${quizz.category}</th>
<td> ${dateCreated} </td>
<td>
   ${quizz.quizName}
</td>
<td>
   ${quizz.subject}
</td>
<td>
   ${quizz.difficulty}
</td>
<td>
   ${quizz.questions.length}
</td>
<td>
    <div class="dropdown show">
        <button type="button" class="btn btn-success btn-sm">Select
            action</button>
        <button type="button" class="btn btn-success btn-sm dropdown-toggle"
            data-toggle="dropdown" aria-expanded="false" style="float: right;">
            <span class="caret"></span>
            <span class="sr-only">Toggle Dropdown</span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a class="dropdown-item" role="button" onclick="previewQuiz('${quizz.quizId}')">Preview</a>
            </li>
            <li>
                <a class="dropdown-item" href="/Quizzery/editQuiz?quizId=${quizz.quizId}" role="button">Edit</a>
            </li>
            <li>
                <a class="dropdown-item"   role="button">Analyze</a>
            </li>
            <li role="separator" class="divider"></li>
            <li>
                <a class="dropdown-item" onclick='deleteQuiz("${quizz.quizId}")' role="button">Delete</a>
            </li>
        </ul>
    </div>

</td>
</tr>`; }); 
   userQuizzes.innerHTML= innerString;               
      }    
});

function previewQuiz(qId){
	$("#previewQuiz").modal();
	console.log("preview pressed", qId)

let	htmlContent="";
let htmlTitle="";	
let tick="";
	for (q of quizzes){
		console.log(q.quizId);
		if (q.quizId==qId){
			htmlTitle=q.quizName;
			let questions=q.questions;
			let qNo=1;
			for (question of questions){

				htmlContent+=`<div class="preview_area" style="background-color: white;">`
				let answers=question.answers;
				htmlContent+=`  <br>		
                <hr class="style-eight" style="weight: 2px solid"/>
                 <div class="question_answer_area">
					 <div class="question_area">
                 <strong>
                  <span style="float left"><u>Question #${qNo} </u></span>
                  <p>${question.questionText}</strong></p> </div>
					<div class="answer_area">`;
						qNo++;		
						for (answer of answers) {
							if (answer.correct) tick="checked";
							htmlContent+=
								`<input type="radio" id="answer.answerId" name="${question.questionId}" ${tick}>
								 <label for="male">${answer.answerText}</label><br>  `;
							tick="";
       			}
						htmlContent+=`</div></div>`
						
			}
			$("#previewQuiz .modal-title").html(htmlTitle);
			$("#quizQuestions").html(htmlContent);
			break;
		}
		
		
	}
}


$('#quizPreviewCloseButton').click(function(e){
	$("#previewQuiz").modal();
	$("#previewQuiz .modal-title").html('');
	$("#quizQuestions").html('');	
});

    
 




