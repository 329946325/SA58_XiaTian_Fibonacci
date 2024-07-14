import React, { useState } from 'react';
import './styles.css';
import 'bootstrap/dist/css/bootstrap.min.css';

const FormComponent = () => {
    const [targetAmount, setTargetAmount] = useState('');
    const [coinDenominations, setCoinDenominations] = useState('');
    const [optimizedResult, setOptimizedResult] = useState([]);

    const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await fetch(`${API_BASE_URL}/api/coins/optimize`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    targetAmount: parseFloat(targetAmount),
                    coinDenominations: coinDenominations.split(',').map(denomination => parseFloat(denomination))
                }),
                mode: 'cors',
                credentials: 'same-origin'
            });
            const data = await response.json();
            console.log('API response:', data);
            setOptimizedResult(data);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <div className="container mt-5">
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="targetAmount" className="form-label">Target Amount:</label>
                    <input
                        type="number"
                        className="form-control"
                        id="targetAmount"
                        value={targetAmount}
                        onChange={(e) => setTargetAmount(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="coinDenominations" className="form-label">Coin Denominations (comma-separated):</label>
                    <input
                        type="text"
                        className="form-control"
                        id="coinDenominations"
                        value={coinDenominations}
                        onChange={(e) => setCoinDenominations(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary">Change Coins</button>
            </form>

            <div className="mt-4">
                {optimizedResult.length > 0 && (
                    <div>
                        <h4>Coin Change Result:</h4>
                        <ul className="list-group">
                            {optimizedResult.map((coin, index) => (
                                <li key={index} className="list-group-item">{coin}</li>
                            ))}
                        </ul>
                    </div>
                )}
                {optimizedResult.length === 0 && (
                    <p className="text-muted mt-3">No result to display. Please enter valid inputs and click 'Optimize Coins'.</p>
                )}
            </div>
        </div>
    );
};

export default FormComponent;
