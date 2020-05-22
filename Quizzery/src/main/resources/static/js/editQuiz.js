var index=2

const save_question_btn = document.querySelector("#save_question_btn");
save_question_btn.addEventListener("click", function () {
 console.log("pressed");
 var questionTxt = tinymce.get('expandText').getContent();
 console.log(questionTxt);

 let ans=[];
 let question;

 for (i=1;i<index;i++){
console.log(index, i);
console.log($('.ans-'+i+' .ansText').val());
console.log($('.ans-'+i+' .ansCor').is(':checked'));
ans.push({answerText:$('.ans-'+i+' .ansText').val(), correct:$('.ans-'+i+' .ansCor').is(':checked')})
 }
console.log(ans);

 question={questionText:questionTxt, points:1, answers:ans}
 //console.log(question);
 
 var Q=JSON.stringify(question);
 
 
 
 console.log(Q);
URL='http://localhost:8080/Quizzery/quizzes/UpnISJsGC92a74kRpUQz/questions';

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

