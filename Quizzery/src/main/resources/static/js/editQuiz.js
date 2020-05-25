
const userQuestions = document.getElementById("userQuestions");
let questions = [];
let editingQuestionId;





function fillModal(questId) {
    console.log(questId);
    editingQuestionId=questId;
    
    for (q of questions) {
        if (q.questionId == questId) {
            tinyMCE.activeEditor.setContent(q.questionText, { format: 'raw' });
            console.log(q.questionId)

            // modal form / creating options dynamically

     
            
            $(".multi-field-wrapper").each(function () {
                var $wrapper = $(".multi-fields", this);
                for (let i = 0; i < q.answers.length; i++) {
                    if (i == 0) {
                        $(".multi-field:first-child", $wrapper)
                            .find("input")
                            .val(q.answers[i].answerText);
                       
                       
                        
                       
                        if(q.answers[i].correct===true){
                        	 console.log(q.answers[i].correct)
                        	
                       	 $(".multi-field:first-child", $wrapper)
                            .find("#correctBox")
                            .prop( "checked", true );
            
                      }else{
                    	  
                    	  $(".multi-field:first-child", $wrapper)
                          .find("#correctBox")
                          .prop( "checked", false );
                    	  
                      }
                        
                        
                        
                    } else {
                        console.log(typeof (q.answers[i].correct));
                        $(".multi-field:first-child", $wrapper)
                            .clone(true)
                            .appendTo($wrapper)
                            .find("input")
                            .val(q.answers[i].answerText);
                        
                     
                        
                        if(q.answers[i].correct===true){
                        	   console.log(q.answers[i].correct)
                        	
                       	 $(".multi-field:first-child", $wrapper)
                            .find("#correctBox")
                            .prop( "checked", true );
                       	
                       }
                        
                        else{
                      	  
                      	  $(".multi-field:first-child", $wrapper)
                            .find("#correctBox")
                            .prop( "checked", false );
                      	  
                        }
                        
                        
                        
                    }
                    console.log(a.answerText);


                    $(".multi-field .remove-field", $wrapper).show();
                }

                $('.multi-field .remove-field', $wrapper).click(function () {
                    if ($('.multi-field', $wrapper).length > 1) {
                        $(this).parent('.multi-field').remove();
                    }
                    adjustButtonVisiblity();
                });


                adjustButtonVisiblity();
                function adjustButtonVisiblity() {
                    if ($('.multi-field', $wrapper).length == 1) {
                        $('.multi-field .remove-field', $wrapper).hide();
                    }
                }
            });

        }
    }
}





const deleteQuestion = (questionId) => {

    $.ajax({
        url: 'http://localhost:8080/Quizzery/quizzes/' + getQueryStringValue("quizId") + '/questions/' + questionId,
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



function getQueryStringValue(key) {
    return decodeURIComponent(window.location.search.replace(new RegExp("^(?:.*[&\\?]" + encodeURIComponent(key)
        .replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));
}



// Getting the quize's questions
$.ajax({
    url: 'http://localhost:8080/Quizzery/quizzes/' + getQueryStringValue("quizId") + "/questions/",
    type: 'GET',
    dataType: 'json',
    async: false,
    data: "",
    contentType: 'application/json',
    success: response1 => {
        console.log(response1);

        userQuestions.innerHTML = "";
        let innerString = "";
        questions = response1;
        response1.map(function (question) {


            userQuestions.innerHTML += `  <br>
	                 <hr class="style-eight">
	                  <div class="" style="background-color: white;">
	                  <div class="">
	                   <p>${question.questionText}</p>    `;


            for (a of question.answers) {
                userQuestions.innerHTML +=

                    `<input type="radio" id="" name="" >
	                <label for="male">${a.answerText}</label><br>  `;
            }

            console.log(question.questionId);
            userQuestions.innerHTML +=
                `  </div>
	            <div class="">
	                <button class="btn btn-primary" id="${question.questionId}" href="#questionEdit" data-toggle="modal" role="button"  onclick='fillModal(this.id)'>Edit</button>
	                <button class="btn btn-danger" onclick='deleteQuestion("${question.questionId}")'  >Delete</button>
	            </div>
	        </div>  `

        });

    }
});



const readQuestionFromNewModal= ()=>{
	
	 var questionTxt = tinymce.get("expandText").getContent();
    console.log(questionTxt);
    let answerList = [];
    $("div[name=answerContainer]").each(function () {
        let answer = {};
        answer.answerText = $(this).children("input[name=answer]").val();
        answer.correct = $(this).children("input[type=checkbox]").prop('checked');
        answerList.push(answer);
    });
    console.log(answerList);
    var question = {
        questionText: questionTxt,
        points: 1,
        answers: answerList
    }
    let Q = JSON.stringify(question);
    console.log(Q);
    return Q
	
}





// ajax for cteate question

const save_question_btn = document.querySelector("#save_question_btn");
save_question_btn.addEventListener("click", function () {
	


    
        URL = 'http://localhost:8080/Quizzery/quizzes/' + getQueryStringValue("quizId") + "/questions/";

        $.ajax({
            url: URL,
            type: 'POST',
            dataType: 'json',
            data: readQuestionFromNewModal(),
            contentType: 'application/json',
            success: response => {
                console.log(response);
            }
        });
    



});


// ajax for update question



const readQuestionFromEditModal= ()=>{
	
	 var questionTxt = tinymce.get("expandTextEdit").getContent();
   console.log(questionTxt);
   let answerList = [];
   $("div[name=answerContainerEdit]").each(function () {
       let answer = {};
       answer.answerText = $(this).children("input[name=answerEdit]").val();
       answer.correct = $(this).children("input[type=checkboxEdit]").prop('checked');
       answerList.push(answer);
   });
   console.log(answerList);
   var question = {
       questionText: questionTxt,
       points: 1,
       answers: answerList
   }
   let Q = JSON.stringify(question);
   console.log(Q);
   return Q
	
}








const save_question_btn_edit = document.querySelector("#save_question_btn_edit");
save_question_btn_edit.addEventListener("click", function () {

	console.log("inside btn edit")
	
console.log(readQuestionFromEditModal());
	
	console.log(editingQuestionId);
	
    URL = 'http://localhost:8080/Quizzery/quizzes/' + getQueryStringValue("quizId") + "/questions/"+editingQuestionId  ;
    $.ajax({
        url: URL,
        type: 'PUT',
        dataType: 'json',
        data: readQuestionFromEditModal(),
        contentType: 'application/json',
        success: response => {
            console.log(response);
        }
    });
});



