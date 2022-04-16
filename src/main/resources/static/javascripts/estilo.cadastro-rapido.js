var Brewer = Brewer || {};

Brewer.EstiloCadastroRapido = (function() {
	function EstiloCadastroRapido() {
		this.inputNomeEstilo = $('#nomeEstilo');    
	    this.mensagemCadastroEstilo = $('.js-mensagem-cadastro-rapido-estilo');	
	    this.modal = $('#modalCadastroRapidoEstilo');	    		
	    this.formulario = this.modal.find('form');	    	
	    this.urlCadastroEstilo = this.formulario.attr('action');	        
	    this.botaoSalvar = this.modal.find('.js-modal-cadastro-rapido-estilo-btn');
	}
	
	EstiloCadastroRapido.prototype.iniciar = function() {
		this.modal.on('shown.bs.modal', onModalShow.bind(this));    	    	   
	    this.modal.on('hide.bs.modal', onModalClose.bind(this));	    
	    
	    this.formulario.on('submit', function(event) {
	        event.preventDefault();
	    });
	    
	    this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this)); 
	}
	
	function onModalShow() {
		this.inputNomeEstilo.focus();
	}
	
	function onModalClose() {
		this.inputNomeEstilo.val('');
	    this.mensagemCadastroEstilo.addClass('hidden');
        this.formulario.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		const nomeEstilo = this.inputNomeEstilo.val().trim();
	       
	   	$.ajax({
	       url: this.urlCadastroEstilo,
	       method: 'POST',
	       contentType: 'application/json',
	       data: JSON.stringify({ 'nome': nomeEstilo }),
	       error: onSalvarEstiloError.bind(this),
		   success: onSalvarEstiloSuccess.bind(this)        
	   });
	}
	
	function onSalvarEstiloError(object) {
   		const mensagemDeErro = object.responseText; 
   		
   		this.mensagemCadastroEstilo.removeClass('hidden');
   		this.mensagemCadastroEstilo.html('<span><i class="fa  fa-exclamation-circle"></i> ' + mensagemDeErro + '</span>');
   		
   		this.formulario.find('.form-group').addClass('has-error');
    }
   
    function onSalvarEstiloSuccess(estilo) {
		const comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.codigo);
		
		this.modal.modal('hide');
    }	
	
	return EstiloCadastroRapido;
}());

$(function() {
	var estiloCadastroRapido = new Brewer.EstiloCadastroRapido();
	estiloCadastroRapido.iniciar();	    
});