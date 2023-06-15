import axios from 'axios';

export async function getUserInfo() {
  try {
    const response = await axios.get('http://localhost:8081/users/user', {
      withCredentials: true,
    });
    const userData = response.data;
    const authority = userData.authorities[0]?.authority;
    return authority;
  } catch (error) {
    console.error(error);
    return null;
  }
}
