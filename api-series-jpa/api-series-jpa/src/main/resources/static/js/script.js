//autoria: Daphne Nanni Rodrigues, Manuela de Caria Bertanha, Michele Ramos Borowski

//variáveis globais
var series;
var comentarios;
var comentario;
var index = 0;
var x = 0;
var cod = 0;

//ao carregar a página
window.onload = function(){
    loadSeries();
}

//integração com o método GET - Séries
function loadSeries(){  
    document.getElementById('gallery').innerHTML = "";
    callAPI('/api/series', 'GET', function(status, response){
         if (status === 200){
            series = response;
            var section = document.getElementById('gallery');
            var str = "";
            console.log(series);
            for (var i=0; i<series.length; i++){
                cod = series[i].id;
                str += "<article>";
                    str += "<div class='capa'><img alt='capa série "+series[i].title+"'src='" +series[i].url+"'/></div>"
                    str += "<button onclick='showDescription("+i+")'> + </button>";
                    str += "<div class='inicio' id='div"+i+"'>";
                        str += "<h2>" + series[i].title + "</h2>";
                        str += "<p> Ano:" + " " + series[i].year + "</p>";
                        str += "<p> Gênero:" + " " + series[i].genre + "</p>";
                        str += "<p> Sinopse:" + " " + series[i].synopsis + "</p>";
                        str += "<p> Direção:" + " " + series[i].director + "</p>";
                        str += "<p> Elenco Principal:" + " " + series[i].mainCast + "</p>";
                        str += "<p> Temporadas:" + " " + series[i].seasons + "</p>";
                        str += "<p> Streaming:" + " " + series[i].streaming + "</p>";
                        str += "<p> Rating: " + " " + series[i].rating + "</p>";
                        str += "<p> Prêmios:" + " " + series[i].awards + "</p>";
                    str += "</div>"
                    str += "<footer>";
                        str += "<button onclick = updateSerie('" + i + "')>Editar</button>";
                        str += "<button onclick = deleteSerie('"+ series[i].id +"')>Apagar</button>";
                        str += "<button onclick = loadComentarios('" + cod + "')> Comentários </button>";
                        str += `<div id="getBlock${series[i].id}" style="display:none" class="comment_form"> <textarea id = 'inputComentario${series[i].id}' placeholder='Adicionar Comentário'></textarea><button onclick = commentSerie('${i}','${series[i].id}')>Enviar Comentário</button></div>`;
                        str += `<button id="addComment${series[i].id}" onclick=showComentarioForm('${series[i].id}')>Adicionar comentário</button>`;
                    str += "</footer>";
                str += "</article>";
            }
            section.innerHTML += str;
        }
       // console.log(status);
       // console.log(response);
    });
}

//ao clicar no botão que mostra os dados da série
function showDescription(i){
    if (document.getElementById("div"+i).className=="fim"){
        document.getElementById("div"+i).className="inicio"
    }
    else {
        document.getElementById("div"+i).className="fim"
    }
}

//integração com o método POST - Séries
function createSerie(){
    event.preventDefault();
    var newSerieA = {
        url: document.getElementById('inputUrlA').value,
        title: document.getElementById('inputTitleA').value,
        synopsis: document.getElementById('inputSynopsisA').value,
        director: document.getElementById('inputDirectorA').value,
        mainCast: document.getElementById('inputMainCastA').value,
        year: document.getElementById('inputYearA').value,
        seasons: document.getElementById('inputSeasonsA').value,
        genre: document.getElementById('inputGenreA').value,
        streaming: document.getElementById('inputStreamingA').value,
        rating: document.getElementById('inputRatingA').value,
        awards: document.getElementById('inputAwardsA').value
    }

    callAPI('/api/series', 'POST', function(status, response){
        if (status === 200){
            console.log(status);
            loadSeries();
            document.getElementById('inputUrlA').value = "";
            document.getElementById('inputTitleA').value = "";
            document.getElementById('inputSynopsisA').value = "";
            document.getElementById('inputDirectorA').value = "";
            document.getElementById('inputMainCastA').value = "";
            document.getElementById('inputYearA').value = "";
            document.getElementById('inputSeasonsA').value = "";
            document.getElementById('inputGenreA').value = "";
            document.getElementById('inputStreamingA').value = "";
            document.getElementById('inputRatingA').value = "";
            document.getElementById('inputAwardsA').value = "";
        }else{
            console.log('erro' + status);
        }
    }, newSerieA);
    window.location.reload();
}

//integração com o método PUT (update) - Séries
function updateSerie(i){
    this.index = i;
    fillForm();
}

function fillForm(){
    event.preventDefault();
    document.getElementById('seriesId').value = series[index].id;
    document.getElementById('inputUrl').value = series[index].url;
    document.getElementById('inputTitle').value = series[index].title;
    var protegeTitulo = document.getElementById('inputTitle');
    protegeTitulo.disabled = true;
    document.getElementById('inputSynopsis').value = series[index].synopsis;
    document.getElementById('inputDirector').value = series[index].director;
    document.getElementById('inputMainCast').value = series[index].mainCast;
    document.getElementById('inputYear').value = series[index].year;
    var protegeAno = document.getElementById('inputYear');
    protegeAno.disabled = true;
    document.getElementById('inputSeasons').value = series[index].seasons;
    document.getElementById('inputGenre').value = series[index].genre;
    var protegeGenero = document.getElementById('inputGenre');
    protegeGenero.disabled = true;
    document.getElementById('inputStreaming').value = series[index].streaming;
    document.getElementById('inputRating').value = series[index].rating;
    document.getElementById('inputAwards').value = series[index].awards;
}

function sendUpdate(){
    event.preventDefault();
    var newSerie = series[index];
    var serieId = document.getElementById('seriesId').value;
    newSerie.url = document.getElementById('inputUrl').value;
    newSerie.title = document.getElementById('inputTitle').value;
    newSerie.synopsis = document.getElementById('inputSynopsis').value;
    newSerie.director = document.getElementById('inputDirector').value;
    newSerie.mainCast = document.getElementById('inputMainCast').value;
    newSerie.year = document.getElementById('inputYear').value;
    newSerie.seasons = document.getElementById('inputSeasons').value;
    newSerie.genre = document.getElementById('inputGenre').value;
    newSerie.streaming = document.getElementById('inputStreaming').value;
    newSerie.rating = document.getElementById('inputRating').value;
    newSerie.awards = document.getElementById('inputAwards').value;

    callAPI('/api/series/' + serieId , 'PUT', function(status, response){
    if (status === 200){
        loadSeries();
        document.getElementById('inputUrl').value = "";
        document.getElementById('inputTitle').value = "";
        var liberaTitulo = document.getElementById('inputTitle');
        liberaTitulo.disabled = false;
        document.getElementById('inputSynopsis').value = "";
        document.getElementById('inputDirector').value = "";
        document.getElementById('inputMainCast').value = "";
        document.getElementById('inputYear').value = "";
        var liberaAno = document.getElementById('inputYear');
        liberaAno.disabled = false;
        document.getElementById('inputSeasons').value = "";
        document.getElementById('inputGenre').value = "";
        var liberaGenero = document.getElementById('inputGenre');
        liberaGenero.disabled = false;
        document.getElementById('inputStreaming').value = "";
        document.getElementById('inputRating').value = "";
        document.getElementById('inputAwards').value = "";

    } else{
        alert('Não foi possível atualizar a série.');
    }
    }, newSerie);
}

//integração com o método DELETE - Séries
function deleteSerie(id){
    event.preventDefault();
    var confirmed = confirm("Deseja realmente apagar essa série?");
    if (confirmed){
        callAPI('/api/series/'+ id, 'DELETE', function(status, response){
            if (status === 200){
                loadSeries();
            } else {
                //alert ("Não foi possível apagar a série. Tente novamente!");
                loadSeries();
            }
        });
    }
}

//integração com o método GET - Comentários
function loadComentarios(serieId){
    document.getElementById('gallery').innerHTML = "";
    callAPI(`/api/series/${serieId}/comentarios`, 'GET', function(status, response){
        if (status === 200){
            comentarios = response;
            var section = document.getElementById('gallery');
            var str = "";
            console.log(comentarios);
            if (comentarios.length == 0) {
                str += "<article>";
                str += "<p> Nenhum comentário foi registrado para essa série :( </p>";
                str += "<footer> <button onclick='loadSeries()'> <<< </button> </footer>";                
                str += "</article>";
            } else {
                for (var i=0; i<comentarios.length; i++){
                    str += "<article>";
                    str += "<p>" + comentarios[i].texto + "</p>";
                    str += "<footer> <button onclick='loadSeries()'> <<< </button> </footer>";                
                    str += "</article>";
                    //str += "<p> Comentário:" + " " + comentarios[i].texto + " </p>";
                }
            }
            section.innerHTML += str;
        }
    });
}

//ao clicar no botão para adicionar um comentário
function showComentarioForm(id){
    document.getElementById(`getBlock${id}`).style.display = "block";
    document.getElementById(`addComment${id}`).style.display = "none";
}

function commentSerie(i, elementId) {
    document.getElementById(`getBlock${elementId}`).style.display = "none";
    document.getElementById(`addComment${elementId}`).style.display = "block";

    //integração com o método POST - Comentários
    let comentario = document.getElementById(`inputComentario${series[i].id}`).value;
    console.log(comentario);
    callAPI(`/api/series/${series[i].id}/comentarios`, 'POST', function(status, response){
        if (status === 200){
            console.log(status);
            loadSeries();
            document.getElementById(`inputComentario${series[i].id}`).value = "";
        } else {
            console.log('erro' + status);
        }
    }, comentario);
    window.location.reload();
}


//função principal integrada com o banco de dados
function callAPI(url, method, callback, data){
    var xhr = new XMLHttpRequest();
   
    xhr.responseType = 'json';
    xhr.open(method, url, true);
    if (method == 'POST' || method == 'PUT' || method == 'UPDATE'){
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    }
    xhr.onload = function(){
        callback(xhr.status, xhr.response);
    }

    if (data){
        xhr.send( JSON.stringify(data) );
    }
    else{
        xhr.send();
    }

}