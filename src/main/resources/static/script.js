	
window.addEventListener('load', () => {
	overridesParams();
	document.querySelectorAll("#sidebar ul li a").forEach(e => {
		if (location.pathname.includes("/"+e.dataset.href)){
			e.classList.toggle("active")
		}
	})
	
})		

function overridesParams(){
	const url = new URL(location.href);
	const params = url.search.replaceAll("?","").split("&");
 	params.forEach(item => {
		 const [key, value] = item.split("=");
		 
		 document.getElementById(key).value = value;
	 })
}
		function downloadExcel(url){
			fetch(url)
			.then(resp => resp.blob())
			.then(blob => {
					const uri = window.URL.createObjectURL(new Blob([blob]));
                    const a = document.createElement('a');
                    a.href = uri;
                    a.download = url.split("/")[url.split("/").length -1 ]+'.xlsx';
                    a.click();
                    window.URL.revokeObjectURL(uri);
			})
		}
		
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
			document.querySelector("#objectForm").addEventListener("submit", e=>e.preventDefault());		
			if (condition()) fethDelete(`${location.origin}/${path}/delete/${e.dataset.id}`);
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