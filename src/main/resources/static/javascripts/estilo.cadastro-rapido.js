$(function() {
    const inputNomeEstilo = $('#nomeEstilo');    
    const mensagemCadastroEstilo = $('.js-mensagem-cadastro-rapido-estilo');

    const modal = $('#modalCadastroRapidoEstilo');
    modal.on('shown.bs.modal', function() {
        inputNomeEstilo.focus();
    });
	modal.on('hide.bs.modal', function() {
        inputNomeEstilo.val('');
        mensagemCadastroEstilo.addClass('hidden');
        formulario.find('.form-group').removeClass('has-error');
    });
	
    const formulario = modal.find('form');
    formulario.on('submit', function(event) {
        event.preventDefault();
    });

    const urlCadastroEstilo = formulario.attr('action');
        
    const botaoSalvar = modal.find('.js-modal-cadastro-rapido-estilo-btn');
    botaoSalvar.on('click', function() {
       const nomeEstilo = inputNomeEstilo.val().trim();
       
       $.ajax({
           url: urlCadastroEstilo,
           method: 'POST',
           contentType: 'application/json',
           data: JSON.stringify({ 'nome': nomeEstilo }),
           error: function(object) {
		   		const mensagemDeErro = object.responseText; 
		   		
		   		mensagemCadastroEstilo.removeClass('hidden');
		   		mensagemCadastroEstilo.html('<span><i class="fa  fa-exclamation-circle"></i> ' + mensagemDeErro + '</span>');
		   		
		   		formulario.find('.form-group').addClass('has-error');
		   },
		   success: function(estilo) {
				const comboEstilo = $('#estilo');
				comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
				comboEstilo.val(estilo.codigo);
				
				modal.modal('hide');
		   }       
       });
    });       
});