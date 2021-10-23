/* sudo firewall-cmd --zone=public --add-port=8080/tcp --permanent*/
function registro(){
   var elemento= {
       name: $("name").val(),
       description: $("#description").val(),
       price: $("#price").val()
   }
   var dataToSend=JSON.stringify(elemento);
   //JSON = JavaScript Object Notation
    $.ajax({
        dataType: 'json',
        data:dataToSend,
        url:"http://localhost:8080/api/Car/save",
        type:'POST',
        success:function(response){
            console.log(response);

        },
        error: function(jqXHR, textStatus, errorThrown){
        }

    });
}
function obtenerItems(){
    $.ajax({
        //dataType:'json',
        //contentType: "application/json",
        url:"http://localhost:8080/api/Car/all",
        type:'GET',
        success:function(response){
            var misItems=response.items;
            for(i=0;i<misItems.length;i++){
                console.log(misItems[i]);
                $("#miResultado").append("<tr>");
                $("#miResultado").append("<td>"+misItems[i].name+"</td>");
                $("#miResultado").append("<td>"+misItems[i].description+"</td>");
                $("#miResultado").append("<td>"+misItems[i].price+"</td>");
                $("#miResultado").append('<td><button onclick="borrar('+misItems[i].id+')">Borrar</button></td>');
                $("#miResultado").append('<td><button onclick="obtenerItemEspecifico('+misItems[i].id+')"></button></td>')
                $("#miResultado").append("</tr>");

            }
            //$("#datos").append(response.items[0].name,response.items[0].email)

        },
        error: function(jqXHR, textStatus, errorThrown){
        }
    });
}

function borrar(id){
    var dato={
        id:id
    }
    var dataToSend=JSON.stringify(dato);
    $.ajax({
        dataType:'json',
        data:dataToSend,
        contentType: "application/json",
        url: "http://localhost:8080/api/Car/save",
        type:'DELETE',
        success:function(response){
            console.log(response);
        },
        error: function(jqXHR, textStatus, errorThrown){
        }
    });

}
function obtenerItemEspecifico(idItem){
    $.ajax({
        dataType:'json',
        //contentType: "application/json",
        url: "http://localhost:8080/api/Car/id",
        type:'GET',
        success:function(response){
            console.log(response);
            var item=response.items[0];


            $("#name").val(item.price);
            $("#description").val(item.price)
            $("#price").val(item.price)

        },
        error: function(jqXHR, textStatus, errorThrown){
        }
    });
}

function actualizar() {
    var dato = {
        name: $("#name").val(),
        description: $("#description").val(),
        price: $("#price").val()
    }

    var dataToSend = JSON.stringify(dato);
    $.ajax({
        dataType: 'json',
        data: dataToSend,
        contentType: 'application/json',
        url: "http://localhost:8080/api/Car/save",
        type: 'PUT',
        success: function (response) {
            console.log(response);

        },
        error: function (jqXHR, textStatus, errorThrown) {

        }

    });
}