//temporizador
const btnConfig = document.querySelector(".config")
const sonidoBocina = document.querySelector(".sonido")
const sonidoBocinaNulo = document.querySelector(".sonidoNulo")
const textoTiempo = document.querySelector(".tiempo")
const pantallaCell = document.querySelector(".pantallaCelular")
const sol = document.querySelector(".sol");
const nube1 = document.querySelector(".nube1")
const nube2 = document.querySelector(".nube2")
const nub1 = document.getElementById("nub1")
const nub2 = document.getElementById("nub2")
const shon0 = document.querySelector(".shon0")
const shon2 = document.querySelector(".shon2")
const cabezaShon0 = document.querySelector(".cabezaShon0")
const cabezaShon2 = document.querySelector(".cabezaShon2")
const suelo0 = document.querySelector(".suelo0")
const suelo1 = document.querySelector(".suelo1")
const suelo2 = document.querySelector(".suelo2")
const suelo3 = document.querySelector(".suelo3")
const alas1 = document.querySelector(".alas1")
const alas2 = document.querySelector(".alas2")
const al1 = document.getElementById("al1")
const al2 = document.getElementById("al2")
const al3 = document.getElementById("al3")
const al4 = document.getElementById("al4")
const al5 = document.getElementById("al5")
const al6 = document.getElementById("al6")
const al7 = document.getElementById("al7")
const al8 = document.getElementById("al8")
const al9 = document.getElementById("al9")
const al10 = document.getElementById("al10")
const arbol = document.querySelector(".arbol")
const contenedorShon1 = document.querySelector(".contenedorShon1")
const cabezaShon1 = document.querySelector(".cabezaShon1")
const shon1 = document.querySelector(".shon1")
const patita1 = document.querySelector(".patita1")
const patita2 = document.querySelector(".patita2")
const patita3 = document.querySelector(".patita3")
const tnt = document.querySelector(".tnt")
const cuboBlanco = document.querySelector(".cuboBlanco")
let colorBase
let reproductor = false
const bocina = new Audio("../sonidos/HoldingHands-Jeremy Korpas.mp3");
const sonidoDia = new Audio("../sonidos/birds.mp3");
const sonidoNoche = new Audio("../sonidos/grillos.mp3");
const sonidoNube = new Audio("../sonidos/nubes.mp3");
const sonidoAlas = new Audio("../sonidos/wings.mp3");
const sonidoBeep = new Audio("../sonidos/ticking-bomb-90319.mp3");
const sonidoBomba = new Audio("../sonidos/a-bomb-139689.mp3");
const sonidoShon = new Audio("../sonidos/shon.mp3");

function diaNoche(){
     let colorDia = '#8EC5FC'
     let colorNoche = '#07204a'

    if (colorBase !== colorDia){
        pantallaCell.style.setProperty('background-color',`${colorDia}`)
        colorBase = colorDia
        sonidoNoche.pause()
        sonidoNoche.currentTime = 0
        sonidoDia.play()
        sonidoDia.loop()
     } else {
        pantallaCell.style.setProperty('background-color',`${colorNoche}`)
        colorBase = colorNoche 
        sonidoDia.pause()
        sonidoDia.currentTime = 0
        sonidoNoche.play()
        sonidoNoche.loop()
     }
}

function disolverNube(){
  nub1.addEventListener("click", function() {
    sonidoNube.play()
    nub1.classList.remove("active"); // Elimina la animación previa
    void nub1.offsetWidth; // Reinicia la animación forzando un "reflow"
    nub1.classList.add("active"); // Vuelve a activar la animación
})
  nub2.addEventListener("click", function() {
    sonidoNube.play()
    nub2.classList.remove("active"); // Elimina la animación previa
    void nub2.offsetWidth; // Reinicia la animación forzando un "reflow"
    nub2.classList.add("active"); // Vuelve a activar la animación
})
}

suelo1.addEventListener("click", function() {
    activarSonidoShon()
    cabezaShon0.classList.remove("moverCabeza"); 
    void cabezaShon0.offsetWidth; 
    cabezaShon0.classList.add("moverCabeza");
    shon0.classList.remove("moverCuerpo"); 
    void shon0.offsetWidth
    shon0.classList.add("moverCuerpo");
    
})
suelo2.addEventListener("click", function() {
    activarSonidoShon()
    cabezaShon0.classList.remove("moverCabeza"); 
    void cabezaShon0.offsetWidth; 
    cabezaShon0.classList.add("moverCabeza");
    shon0.classList.remove("moverCuerpo"); 
    void shon0.offsetWidth
    shon0.classList.add("moverCuerpo");
    
})
suelo0.addEventListener("click", function() {
    activarSonidoShon()
    cabezaShon2.classList.remove("moverCabeza"); 
    void cabezaShon2.offsetWidth; 
    cabezaShon2.classList.add("moverCabeza");
    shon2.classList.remove("moverCuerpo"); 
    void shon2.offsetWidth
    shon2.classList.add("moverCuerpo");
})
suelo3.addEventListener("click", function() {
    activarSonidoShon()
    cabezaShon2.classList.remove("moverCabeza"); 
    void cabezaShon2.offsetWidth; 
    cabezaShon2.classList.add("moverCabeza");
    shon2.classList.remove("moverCuerpo"); 
    void shon2.offsetWidth
    shon2.classList.add("moverCuerpo");
})
arbol.addEventListener("click", function() {
    sonidoAlas.play()
    al1.classList.remove("vuelo1"); 
    void al1.offsetWidth; 
    al1.classList.add("vuelo1");
    al2.classList.remove("vuelo1"); 
    void al2.offsetWidth; 
    al2.classList.add("vuelo1");
    al3.classList.remove("vuelo2"); 
    void al3.offsetWidth; 
    al3.classList.add("vuelo2");
    al4.classList.remove("vuelo2"); 
    void al4.offsetWidth; 
    al4.classList.add("vuelo2");
    al5.classList.remove("vuelo3"); 
    void al5.offsetWidth; 
    al5.classList.add("vuelo3");
    al6.classList.remove("vuelo3"); 
    void al6.offsetWidth; 
    al6.classList.add("vuelo3");
    al7.classList.remove("vuelo4"); 
    void al7.offsetWidth; 
    al7.classList.add("vuelo4");
    al8.classList.remove("vuelo4"); 
    void al8.offsetWidth; 
    al8.classList.add("vuelo4");
    al9.classList.remove("vuelo5"); 
    void al9.offsetWidth; 
    al9.classList.add("vuelo5");
    al10.classList.remove("vuelo5"); 
    void al10.offsetWidth; 
    al10.classList.add("vuelo5");
})
cuboBlanco.addEventListener("click", function() {
    sonidoBeep.play()
    tnt.classList.remove("bombaFase1"); // Elimina la animación previa
    void tnt.offsetWidth; // Reinicia la animación forzando un "reflow"
    tnt.classList.add("bombaFase1");
    setTimeout(() => {
        sonidoBomba.play() // Reproduce el segundo sonido después de 3 segundos
    }, 2500);
    cuboBlanco.classList.remove("bombaFase2"); // Elimina la animación previa
    void cuboBlanco.offsetWidth; // Reinicia la animación forzando un "reflow"
    cuboBlanco.classList.add("bombaFase2"); // Vuelve a activar la animación
})
function activarSonidoShon(){
    sonidoShon.play(); 

    setTimeout(() => {
        sonidoShon.pause()
        sonidoShon.currentTime = 0 // Reinicia el audio al inicio
     }, 3500);
}
contenedorShon1.addEventListener("click", function() {
    activarSonidoShon()
    this.classList.remove("caminar")
    void this.offsetWidth
    this.classList.add("caminar")
    cabezaShon1.classList.remove("moverCabeza"); // Elimina la animación previa
    void cabezaShon1.offsetWidth; // Reinicia la animación forzando un "reflow"
    cabezaShon1.classList.add("moverCabeza");
    patita1.classList.remove("active1"); // Elimina la animación previa
    void patita1.offsetWidth; // Reinicia la animación forzando un "reflow"
    patita1.classList.add("active1");
    patita2.classList.remove("active2"); // Elimina la animación previa
    void patita2.offsetWidth; // Reinicia la animación forzando un "reflow"
    patita2.classList.add("active2"); 
    patita3.classList.remove("active1"); // Elimina la animación previa
    void patita3.offsetWidth; // Reinicia la animación forzando un "reflow"
    patita3.classList.add("active1");// Vuelve a activar la animación
})

function ventanaDeConfiguracion(){
    let texto = prompt("Coloca el tiempo usando el formato MM:SS. Al aceptar se cerrará esta ventana y la configuración se verá bloqueada hasta que el tiempo se termine:");
    if (texto !== null && texto.trim() !== "" && texto.length == 5) {
        textoTiempo.innerText = texto;
}
}
sonidoBocinaNulo.addEventListener("click", function() {
    if(reproductor){
        sonidoBocinaNulo.style.setProperty('opacity',"1")
        bocina.pause()
    }else{
        sonidoBocinaNulo.style.setProperty('opacity',"0")
        bocina.play()
        bocina.loop = true;
    }
    reproductor = !reproductor 
});

// let limiteTiempo = 8
 
// function estableceTiempo(){
//     for(let i = limiteTiempo; i >= 0; i--){
//         setTimeout(() => {
//             console.log(i)
//           }, 1000 * (limiteTiempo - i))
//     }

// }
// estableceTiempo()