import { useState } from 'react';
import { test } from 'vitest';
import userEvent from '@testing-library/user-event';
import { render, screen } from '@testing-library/react';

function TestButton() {
  const [saved, setSaved] = useState(false);

  return (
    <button onClick={() => setSaved(true)}>{saved ? 'Saved' : 'Save'}</button>
  );
}

test('updates the button after clicking', async () => {
  const user = userEvent.setup();

  render(<TestButton />);

  await user.click(screen.getByRole('button', { name: 'Save' }));
});
