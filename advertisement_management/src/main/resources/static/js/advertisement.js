document.addEventListener('DOMContentLoaded', () => {
    const createAdForm = document.getElementById('createAdForm');
    const adList = document.getElementById('adList');
    const adDetails = document.getElementById('adDetails');

    fetchAllAdvertisements();

    createAdForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const adTitle = e.target.adTitle.value;
        const adUrl = e.target.adUrl.value;

        const advertisement = {
            title: adTitle,
            adUrl: adUrl
        };

        createAdvertisement(advertisement);
        e.target.reset();
    });

    function fetchAllAdvertisements() {
        fetch('/api/advertisements/all')
            .then(response => response.json())
            .then(data => {
                adList.innerHTML = '';
                data.forEach(ad => {
                    const li = document.createElement('li');
                    li.textContent = ad.title;
                    li.addEventListener('click', () => fetchAdvertisementDetails(ad.id));
                    adList.appendChild(li);
                });
            });
    }

    function createAdvertisement(advertisement) {
        fetch('/api/advertisements/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(advertisement)
        })
            .then(() => fetchAllAdvertisements());
    }

    function fetchAdvertisementDetails(adId) {
        fetch(`/api/advertisements/${adId}`)
            .then(response => response.json())
            .then(data => {
                adDetails.innerHTML = `
                    <p><strong>Title:</strong> ${data.title}</p>
                    <p><strong>URL:</strong> <a href="${data.adUrl}" target="_blank">${data.adUrl}</a></p>
                `;
            });
    }
});