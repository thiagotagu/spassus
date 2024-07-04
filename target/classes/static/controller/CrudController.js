const CrudController = (function() {

	const init = function() {

		$("#btnNovo").on("click", function() {
			novo();
		});

		$("#btnCancelar").on("click", function() {
			$('form').get(0).reset();
			$("#formulario").hide();
		});

		setInterval(function() { $('#msgSucesso').remove() }, 5000);


		if ($('#id').val() != "") {
			$("#formulario").show();
		}
		
		
	}

	const novo = function() {
		$('input').val("");
		$("#formulario").show();
	}

	const deletar = function(id) {
		$.ajax({
			url: '/'+$('#crud').val()+'/' + id,
			type: 'Post',
			success: function(result) {
				location.reload();
			}
		});
	}

	return {
		init: init,
		deletar: deletar
	}

})();

$(document).ready(function() {
	CrudController.init();
});

 
