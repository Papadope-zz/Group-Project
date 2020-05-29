
const userQuestions = document.getElementById("userQuestions");
let questions = [];
let editingQuestionId;


const getQuestionFromList = (id, questionList)=>{
	
	 for (q of questionList) {
	        if (q.questionId == id) {
	        	
	        	return q;
	        }
	
     }

}

function fillModal(questId) {
    console.log(questId);
    editingQuestionId=questId;
    
  
           q= getQuestionFromList(questId,questions);
        
            tinyMCE.activeEditor.setContent(q.questionText, { format: 'raw' });
            console.log(q.questionId)

          
          

          
       // remove fields from previous edits
            let removeCounter=0;                                  
            $("div[id=edit-multi-field]").each(function () {
           
            removeCounter++;
          
            if(removeCounter>1)    {  
            	 console.log("remove counter : "+removeCounter);
            	this.remove();
            	} 
            
           
        	
            });
               
            
             $("#hiddenQuestionId").attr('value',questId );  //filling hidden field with question id
            
            
            
      // modal form , creating options dynamically
     
            
           $("#edit_multi-field-wrapper").each(function () {
        	   
        	  
                var $wrapper = $("#edit-multi-fields", this);
                for (let i = 0; i < q.answers.length; i++) {
               
            
                	
                    if (i == 0) {
                    	
                   $("#edit-multi-field:first-child", $wrapper)
                            .find("input")                                 // filling answer input
                            .val(q.answers[i].answerText);
                            
                             $("#edit-multi-field:first-child", $wrapper)
                            .find("input[type=checkbox]")                  // filling correct answer
                            .prop( "checked", q.answers[i].correct );
                       
                             $("#edit-multi-field:first-child", $wrapper)
                             .find("#hiddenAnswerId")  
                             .attr('value',q.answers[i].answerId );                 //adding hidden field with answer id
                             
                             
                             
                             
                 
                        
                    } else {
                        console.log(q.answers[i].correct+" "+q.answers[i].answerText)
                        $("#edit-multi-field:first-child", $wrapper)
                            .clone(true)                                     //if there is more than one answers make new fields
                            .appendTo($wrapper)
                            .find("input")
                            .val(q.answers[i].answerText)  ;
                         
                            $("#edit-multi-field:last-child", $wrapper)
                            .find("input[type=checkbox]")
                            .prop( "checked", q.answers[i].correct );
                            

                            $("#edit-multi-field:last-child", $wrapper)
                            .find("#hiddenAnswerId") 
                            .attr('value',q.answers[i].answerId );
                            
                }
                
                    
               $("#edit-multi-field .remove-field", $wrapper).show();
                }
           
                $('#edit-multi-field .remove-field', $wrapper).click(function () {
                    if ($('#edit-multi-field', $wrapper).length > 1) {
                        $(this).parent('#edit-multi-field').remove();
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






const deleteQuestion = (questionId) => {

    $.ajax({
        url: 'http://localhost:8080/Quizzery/quizzes/' + getQueryStringValue("quizId") + '/questions/' + questionId,
        type: 'DELETE',
        dataType: 'json',
        data: "",
        async: false,
        contentType: 'application/json',
        success: response => {
            console.log(response);
            window.location.href = "/Quizzery/dashboard"
        }
        
    })
    
      window.location.reload();
    
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


            innerString += `  <br>
	                 <hr class="style-eight">
	                  <div class="" style="background-color: white;">
	                  <div class="">
	                   <p>${question.questionText}</p>    `;


            for (a of question.answers) {
            	innerString +=

                    `<input type="radio" id="" name="" >
	                <label for="male">${a.answerText}</label><br>  `;
            }

            console.log(question.questionId);
            innerString +=
                `  </div>
	            <div class="">
	                <button class="btn btn-primary" id="${question.questionId}" href="#questionEdit" data-toggle="modal" role="button"  onclick='fillModal(this.id)'>Edit</button>
	                <button class="btn btn-danger" onclick='deleteQuestion("${question.questionId}")' href=  >Delete</button>
	            </div>
	        </div>  `

        });
        
        
        userQuestions.innerHTML += innerString;

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





// ajax for create question

const save_question_btn = document.querySelector("#save_question_btn");
save_question_btn.addEventListener("click", function () {
	


    
        URL = 'http://localhost:8080/Quizzery/quizzes/' + getQueryStringValue("quizId") + "/questions/";

        $.ajax({
            url: URL,
            type: 'POST',
            dataType: 'json',
            async: false,
            data: readQuestionFromNewModal(),
            contentType: 'application/json',
            success: response => {
                console.log(response);
            }
        });
    

        window.location.reload();

});


// ajax for update question



const readQuestionFromEditModal= ()=>{
	
	 var questionTxt = tinymce.get("expandTextEdit").getContent();
   console.log(questionTxt);
   let answerList = [];
   $("div[name=answerContainerEdit]").each(function () {
       let answer = {};
       answer.answerText = $(this).children("input[name=answerEdit]").val();
       answer.correct = $(this).children("input[type=checkbox]").prop('checked');
       answer.answerId=$(this).children("input[id=hiddenAnswerId]").val();
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
        async: false,
        data: readQuestionFromEditModal(),
        contentType: 'application/json',
        success: response => {
            console.log(response);
        }
    });
    
    window.location.reload();
    
});



