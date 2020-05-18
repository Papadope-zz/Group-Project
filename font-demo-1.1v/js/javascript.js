// Papadope.

// back to top arrow button function
$(window).scroll(function() {
    var height = $(window).scrollTop();
    if (height > 100) {
        $('#back2Top').fadeIn();
    } else {
        $('#back2Top').fadeOut();
    }
});
$(document).ready(function() {
    $("#back2Top").click(function(event) {
        event.preventDefault();
        $("html, body").animate({ scrollTop: 0 }, "slow");
        return false;
    });

});

// toggle data function for every toggle inside the website
function toggle(source) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i] != source)
            checkboxes[i].checked = source.checked;
    }
}

// function that prevent hiding the dropdown menu in table dashboard
$(function() {
    $('.table-responsive').on('shown.bs.dropdown', function(e) {
      var t = $(this),
        m = $(e.target).find('.dropdown-menu'),
        tb = t.offset().top + t.height(),
        mb = m.offset().top + m.outerHeight(true),
        d = 20; // Space for shadow + scrollbar.   
      if (t[0].scrollWidth > t.innerWidth()) {
        if (mb + d > tb) {
          t.css('padding-bottom', ((mb + d) - tb));
        }
      } else {
        t.css('overflow', 'visible');
      }
    }).on('hidden.bs.dropdown', function() {
      $(this).css({
        'padding-bottom': '',
        'overflow': ''
      });
    });
  });

// checkbox delete user Appear / Disappear
$(document).ready(function() {
    
    var $submit = $("#submit_prog").hide(),
        $cbs = $('input[name="delete"]').click(function() {
            $submit.toggle( $cbs.is(":checked") );
        });

});

// text area function 
$('#expandText').click(function () {
  $(this).animate({ height: "70px" }, 500);
  $(this).animate({ width: "300px" }, 500);
});

// text area function 2
$('#exampleTextarea').click(function () {
  $(this).animate({ height: "400px" }, 500);
  $(this).animate({ width: "100%" }, 500);
});

// modal form / creating options dynamically
$('.multi-field-wrapper').each(function() {
  var $wrapper = $('.multi-fields', this);
  $(".add-field", $(this)).click(function(e) {
      $('.multi-field:first-child', $wrapper).clone(true).appendTo($wrapper).find('input').val('').focus();
      $('.multi-field .remove-field', $wrapper).show();
  });
  $('.multi-field .remove-field', $wrapper).click(function() {
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

// popover modal Help Section 
$(document).ready(function(){
  $('[data-toggle="popover"]').popover();
});

