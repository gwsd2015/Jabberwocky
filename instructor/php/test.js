$('.btn-group').on('input', 'change', function(){
   var checkbox = $(this);
   var label = checkbox.parent('label');
   if (checkbox.is(':checked'))  {
      label.addClass('active');
   }
   else {
      label.removeClass('active');
   }
});