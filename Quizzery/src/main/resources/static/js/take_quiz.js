

let quizT = document.getElementById("quizTable");
console.log(quizT);
console.log("js running");


quizzes = [];
performedQuizId="";

//creating users's Quizz Table dynamicaly
$.ajax({
  
    url: 'http://localhost:8080/Quizzery/quizzes',
    type: 'GET',
    dataType: 'json',
    contentType: 'application/json',
    success: response => {
        console.log(response);
        quizzes = response;
        let innerString = ""
        for (quizz of quizzes) {
            innerString +=
                ` <tr>
<td>
   ${quizz.quizName}
</td>
<td>
    Private
</td>
<td>
    Username
</td>
<td>
   <button type="button" class="btn btn-success btn-sm" onclick='performQuiz("${quizz.quizId}")' role="button">Take Quiz</a></button>
</td>
</tr>`;
        }
        console.log(quizT.innerHTML);
        quizT.innerHTML = innerString;

    }

});


function performQuiz(qId){
	
	$("#takeQuiz").modal();
	console.log("takeQuiz pressed", qId)

let	htmlContent="";
let htmlTitle="";	

	for (q of quizzes){
		console.log(q.quizId);
		if (q.quizId===qId){
			htmlTitle=q.quizName;
			let questions=q.questions;
			let qNo=1;
			for (question of questions){
		
				htmlContent+=`<div class="preview_area" style="background-color: white;">`
				let answers=question.answers;
				htmlContent+=`  <br>		
                <hr class="style-eight" style="weight: 2px solid"/>
                 <div class="question_answer_area">
					 <div class="question_area" id="question.questionId" >
                 <strong>
                  <span style="float left"><u>Question #${qNo}</u> </span><p>${question.questionText}</strong></p> </div>
					<div class="answer_area">`;
						qNo++;		
						for (answer of answers) {
			
							htmlContent+=
								`<input type="radio" id="${answer.answerId}" name="${question.questionId}">
								 <label for="male">${answer.answerText}</label><br>  `;
       			}
						htmlContent+=`</div></div>`
						
			}
            
			$("#takeQuiz .modal-title").html(htmlTitle);
			
			$("#quizQuestions").html(htmlContent);
            // $("#previewQuiz .modal-body").html(htmlContent);
            performedQuizId=qId;
            break;
		}
	}
}


$('#submitQuizButton').click(function(e){
    getQuizAnswers(performedQuizId);
	$("#takeQuiz").modal();
	$("#takeQuiz .modal-title").html('');
	$("#quizQuestions").html('');
	
});


function getQuizAnswers(qId){
    let quizAnswers=[];
    let a;
    for (quiz of quizzes){
        if (quiz.quizId===qId){
            let questions=q.questions;
			for (question of questions){
				let answers=question.answers;
                for (answer of answers) {
                 //   a=$('#'+answer.answerId).val();
                //  a=$("input[id=" + answer.answerId + "]:checked").val();
                //  console.log($("input[id=" + answer.answerId + "]:checked").val());
                //  a=$("#"+answer.answerId+":checked").val();
                //  console.log($('#'+answer.answerId+':checked').val());
                 if (document.getElementById(answer.answerId).checked) {quizAnswers.push({questionId:question.questionId, answerId:answer.answerId});
                 break;}
                }
            }
        }
    }
    console.log(quizAnswers);
    requestQuizMark(quizAnswers);
}

function requestQuizMark(quizAnswers){
    json=JSON.stringify({quizId:performedQuizId, correctAnswers:quizAnswers});
    console.log(json);
    $.ajax({
        url: 'http://localhost:8080/Quizzery/results',
        type: 'POST',
        dataType: 'json',
        data: json,
        contentType: 'application/json',
        success: response => {
        	console.log(response);
            alert("Correct: "+response.correctAnswers+" of "+response.totalAnswers+"\n"+"Marks: "+response.givenMark+" of "+response.fullMark);
            }
        });

}