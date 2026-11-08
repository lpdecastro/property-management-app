import { Typography } from '@mui/material';

type Props = {
  title: string;
};

const PlaceholderPage = ({ title }: Props) => {
  return (
    <Typography variant='h4' component='h1'>
      {title}
    </Typography>
  );
};

export default PlaceholderPage;
