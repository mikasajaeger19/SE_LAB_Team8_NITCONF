import { useNavigate } from "react-router-dom";

const Home = () => {
    const navigate = useNavigate();
    const signUpHandler = () => {
        navigate('/register');
    }
    const loginHandler = () => {
        navigate('/login');
    }

    return (
        <div style={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            height: '100vh'
        }}>
            <button onClick={loginHandler} >
                Log in 
            </button>
            <button onClick={signUpHandler} >
                Sign up
            </button>
        </div>
    )
}

export default Home;