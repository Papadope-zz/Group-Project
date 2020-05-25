
const userQuestions = document.getElementById("userQuestions");

const fillModal =(questionId)=>{
	console.log("triggered")
	
	tinyMCE.activeEditor.setContent('<span>some</span> html', {format : 'raw'});
   
	

}
const deleteQuestion =(questionId)=>{
	

	
	
	$.ajax({
		  url: 'http://localhost:8080/Quizzery/quizzes/'+getQueryStringValue("quizId")+'/questions/'+questionId,
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


function getQueryStringValue (key) {  
  return decodeURIComponent(window.location.search.replace(new RegExp("^(?:.*[&\\?]" + encodeURIComponent(key)
		  .replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));  
}  



// Getting the quize's questions
$.ajax({
	  url: 'http://localhost:8080/Quizzery/quizzes/'+getQueryStringValue("quizId")+"/questions/",
	  type: 'GET',
	  dataType: 'json',
	  async: false,
	  data: "",
	  contentType: 'application/json',
	  success: response1 => {
	      console.log(response1);
	 
	      
	      userQuestions.innerHTML = "";
	      let innerString ="" ;

	   response1.map(function(question) {
	  	
		   
		   userQuestions.innerHTML += `  <br>
	                 <hr class="style-eight">
	                  <div class="" style="background-color: white;">
	                  <div class="">
	                   <p>${question.questionText}</p>    `  ;
	                
	
		   $.ajax({
				  url: 'http://localhost:8080/Quizzery/quizzes/'+getQueryStringValue("quizId")+"/questions/"+ question.questionId +"/answers/",
				  type: 'GET',
				  dataType:'json',
				  async: false,
				  data: "",
				  contentType: 'application/json',
				  success: response2 => {
					  
					  console.log("inside answer")
				      console.log(response2);       
					 
				      response2.map(function(answer) {
	                
              userQuestions.innerHTML += 
	             	                
	               `<input type="radio" id="" name="" >
	                <label for="male">${answer.answerText}</label><br>  `;
	                
				      });
				      
				      
				  
				  }
			 });
	     
		 userQuestions.innerHTML +=	      
	          `  </div>
	            <div class="">
	                <button class="btn btn-primary" href="#question" data-toggle="modal" role="button"  onclick='fillModal()'>Edit</button>
	                <button class="btn btn-danger" onclick='deleteQuestion("${question.questionId}")'  >Delete</button>
	            </div>
	        </div>  `
	  
	
	  
	      });
	 
	   }
});
	  








const save_question_btn = document.querySelector("#save_question_btn");
save_question_btn.addEventListener("click", function () {
 console.log("pressed");
 
 
 
 var questionTxt = tinymce.get("expandText").getContent();
 console.log(questionTxt);

 let answerList=[];
 

 $("div[name=answerContainer]").each(function() {

	 let answer={};
	 
	    answer.answerText = $(this).children("input[name=answer]").val();
	    answer.correct = $(this).children("input[type=checkbox]").prop('checked');
	    
	   answerList.push(answer) ;
	   
	});

 console.log(answerList);
 
var question= {
		
		questionText: questionTxt,	
		points:1,
		answers: answerList
		
}

 
 var Q=JSON.stringify(question);

 
 
 console.log(Q);


URL='http://localhost:8080/Quizzery/quizzes/'+getQueryStringValue("quizId")+"/questions/";

$.ajax({
  url: URL,
 type: 'POST',
  dataType: 'json',
  data: Q,
  contentType: 'application/json',
  success: response => {
      console.log(response);
  }
  });

});

