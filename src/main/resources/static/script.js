		window.addEventListener('load', _ => $('#header').load('/header'));		
		
		function fethDelete(url){
			fetch(url)
			.then(resp=> {
				if (resp.status == 200) {
					alert("Se elimino correctamente el item");
					location.reload();
				} else {
					alert("No se pudo eliminar el item")
				}
			})
			.catch(err => alert("ocurrio un error " + err))
		}
		
		function deleteItem(e) {			
			const condition = () => confirm("Seguro que quieres borrar este item? "+ e.dataset.id);
			if (condition()) fethDelete(location.pathname + "/delete/"+e.dataset.id);
		}
		
		function deleteItemInside(e, path) {			
			const condition = () => confirm("Seguro que quieres borrar este item? "+ e.dataset.id);
			if (condition()) fethDelete(`${location.host}/${path}/delete/${e.dataset.id}`);
		}
		
		function getChecked(){
			document.querySelectorAll("input").forEach(item => {
				if (item.checked == true && item.dataset.id != null) console.log(item.dataset.id);
			})
		}
		
		function checkAll(e){
			if (e.checked){
				document.querySelectorAll("input").forEach(item => item.checked = true);	
			} else {
				document.querySelectorAll("input").forEach(item => item.checked = false);
			}
		}