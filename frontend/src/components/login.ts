document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formulario-empresa") as HTMLFormElement;

    form.addEventListener("submit", (event) => {
        event.preventDefault();

        const email = (document.getElementById("email") as HTMLInputElement).value;
        const senha = (document.getElementById("senha") as HTMLInputElement).value;
    });
});
