import type { productsCards } from '@/types/dashboard/index';
import proimg1 from '@/assets/images/products/motor.png';
import axios from 'axios';

interface Chassi {
  idChassi: string;
  // Outras propriedades do objeto chassi, se houver
}

const productsCard: productsCards[] = [];
let updateVueComponent: ((data: productsCards[]) => void) | undefined; // Alteramos para permitir valor indefinido

function getChassiIdFromURL() {
  const urlParts = window.location.pathname.split('/');
  const idChassi = urlParts[urlParts.length - 1];
  return idChassi;
}

async function fetchData() {
  try {
    const idChassi = getChassiIdFromURL();
    if (!idChassi) {
      throw new Error('IdChassi não encontrado na URL.');
    }

    const response = await axios.get(`http://localhost:8081/statistics/installed?idChassi=${idChassi}`, {
      withCredentials: true,
    });

    console.log(response.data);
    const chassis: Chassi[] = response.data;

    chassis.forEach(chassi => {
      const product: productsCards = {
        title: chassi.nome,
        link: '/',
        photo: proimg1,
        salesPrice: 0,
        price: 0,
        rating: 0,
      };
      productsCard.push(product);
    });

    console.log(productsCard);
    if (updateVueComponent) {
      updateVueComponent(productsCard); // Chama a função para atualizar o componente Vue apenas se ela estiver definida
    }
  } catch (error) {
    console.error(error);
  }
}

function setUpdateFunction(fn: (data: productsCards[]) => void) {
  updateVueComponent = fn;
}

async function main() {
  await fetchData();
}

main();

export { productsCard, setUpdateFunction };
