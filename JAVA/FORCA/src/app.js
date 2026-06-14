const API_URL = 'http://endereco ';

// Estado do Jogo
let selectedWord = "";
let category = "";
let guessedLetters = [];
let mistakes = 0;
const MAX_MISTAKES = 6;

// Elementos do DOM
const wordDisplay = document.getElementById('word-display');
const categoryName = document.getElementById('category-name');
const triesCount = document.getElementById('tries-count');
const guessedLettersDisplay = document.getElementById('guessed-letters');
const keyboard = document.getElementById('keyboard');
const resetBtn = document.getElementById('reset-btn');
const modal = document.getElementById('modal');
const modalTitle = document.getElementById('modal-title');
const modalMessage = document.getElementById('modal-message');
const modalClose = document.getElementById('modal-close');
const bodyParts = document.querySelectorAll('.body-part');

// Inicialização
async function initGame() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error('Erro ao buscar palavra do servidor');
        
        const data = await response.json();
        selectedWord = data.palavra.toUpperCase();
        category = data.categoria;
        
        resetGameState();
        renderGame();
    } catch (error) {
        console.error(error);
        alert('Erro ao conectar com o Backend Java. Certifique-se de que o servidor está rodando na porta xxxx.');
    }
}

function resetGameState() {
    guessedLetters = [];
    mistakes = 0;
    categoryName.textContent = category;
    triesCount.textContent = MAX_MISTAKES;
    guessedLettersDisplay.textContent = "Nenhuma";
    
    // Resetar visual da forca
    bodyParts.forEach(part => part.classList.remove('visible'));
    
    // Fechar modal se estiver aberto
    modal.classList.remove('show');
    
    // Gerar teclado
    generateKeyboard();
}

function generateKeyboard() {
    const letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    keyboard.innerHTML = "";
    letters.forEach(letter => {
        const button = document.createElement('button');
        button.textContent = letter;
        button.classList.add('key');
        button.addEventListener('click', () => handleGuess(letter, button));
        keyboard.appendChild(button);
    });
}

function renderGame() {
    // Renderizar a palavra com traços
    wordDisplay.innerHTML = "";
    selectedWord.split("").forEach(letter => {
        const span = document.createElement('span');
        span.classList.add('letter-slot');
        span.textContent = guessedLetters.includes(letter) ? letter : "";
        wordDisplay.appendChild(span);
    });
}

function handleGuess(letter, button) {
    if (guessedLetters.includes(letter) || mistakes >= MAX_MISTAKES) return;

    guessedLetters.push(letter);
    button.disabled = true;

    if (selectedWord.includes(letter)) {
        button.classList.add('correct');
        renderGame();
        checkWin();
    } else {
        button.classList.add('wrong');
        mistakes++;
        updateHangman();
        checkLoss();
    }

    updateStatus();
}

function updateStatus() {
    triesCount.textContent = MAX_MISTAKES - mistakes;
    guessedLettersDisplay.textContent = guessedLetters.join(", ");
}

function updateHangman() {
    if (mistakes <= bodyParts.length) {
        bodyParts[mistakes - 1].classList.add('visible');
    }
}

function checkWin() {
    const isWon = selectedWord.split("").every(letter => guessedLetters.includes(letter));
    if (isWon) {
        showModal("Você Venceu! 🎉", `Parabéns! Você descobriu a palavra: ${selectedWord}`);
    }
}

function checkLoss() {
    if (mistakes >= MAX_MISTAKES) {
        showModal("Fim de Jogo! 💀", `A palavra correta era: ${selectedWord}`);
    }
}

function showModal(title, message) {
    modalTitle.textContent = title;
    modalMessage.textContent = message;
    modal.classList.add('show');
}

// Event Listeners
resetBtn.addEventListener('click', initGame);
modalClose.addEventListener('click', initGame);

// Iniciar ao carregar a página
window.addEventListener('DOMContentLoaded', initGame);
